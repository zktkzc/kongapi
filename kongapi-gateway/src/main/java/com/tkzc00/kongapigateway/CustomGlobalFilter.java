package com.tkzc00.kongapigateway;

import com.tkzc00.kongapiclientsdk.utils.SignUtils;
import com.tkzc00.kongapicommon.model.entity.InterfaceInfo;
import com.tkzc00.kongapicommon.model.entity.User;
import com.tkzc00.kongapicommon.service.InnerInterfaceInfoService;
import com.tkzc00.kongapicommon.service.InnerUserInterfaceInfoService;
import com.tkzc00.kongapicommon.service.InnerUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    private static final List<String> IP_WHITE_LIST = Collections.singletonList("127.0.0.1");
    private static final String INTERFACE_HOST = "http://localhost:8081";
    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;
    @DubboReference
    private InnerUserService innerUserService;
    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("自定义全局过滤器...");

        // 请求日志
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求唯一标识：{}", request.getId());
        String path = INTERFACE_HOST + request.getURI().getPath();
        log.info("请求路径:{}", path);
        String method = request.getMethodValue();
        log.info("请求方法:{}", method);
        log.info("请求参数:{}", request.getQueryParams());
        log.info("请求头:{}", request.getHeaders());

        ServerHttpResponse response = exchange.getResponse();

        // 黑白名单
        String ip = request.getRemoteAddress().getAddress().getHostAddress();
        if (!IP_WHITE_LIST.contains(ip)) {
            log.info("非法请求，拒绝访问");
            return handleNoAuth(response);
        }

        // 用户鉴权
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String nonce = headers.getFirst("nonce");
        String body = headers.getFirst("body");
        User invokeUser = null;
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("获取用户信息异常", e);
        }
        if (invokeUser == null)
            return handleNoAuth(response);
        if (nonce == null || Long.parseLong(nonce) > 10000)
            return handleNoAuth(response);
        Long currentTime = System.currentTimeMillis() / 1000;
        if (timestamp == null || currentTime - Long.parseLong(timestamp) > TimeUnit.MINUTES.toSeconds(5))
            return handleNoAuth(response);
        String secretKey = invokeUser.getSecretKey();
        String serverSign = SignUtils.getSign(body, secretKey);
        if (!serverSign.equals(sign))
            return handleNoAuth(response);

        // 判断请求的模拟接口是否存在
        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("获取接口信息异常", e);
        }
        if (interfaceInfo == null)
            return handleNoAuth(response);
        // 响应日志
        return handleResponse(exchange, chain, interfaceInfo.getId(), invokeUser.getId());
    }

    @Override
    public int getOrder() {
        return -1;
    }

    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    private Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }

    private Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, Long interfaceInfoId, Long userId) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 获取响应的数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 获取响应的状态码
            HttpStatus statusCode = originalResponse.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    // 往返回值中写数据
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                try {
                                    innerUserInterfaceInfoService.invokeCount(interfaceInfoId, userId);
                                } catch (Exception e) {
                                    log.error("调用次数统计异常", e);
                                }
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                sb2.append("<--- {} {} \n");
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                //rspArgs.add(requestUrl);
                                String data = new String(content, StandardCharsets.UTF_8);//data
                                sb2.append(data);
                                log.info("响应结果；{}", data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                // 设置 response 对象为装饰过的对象
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        } catch (Exception e) {
            log.error("网关处理响应结果异常：" + e);
            return chain.filter(exchange);
        }
    }
}
