package com.tkzc00.kongapiinterface.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tkzc00.kongapiinterface.model.User;
import com.tkzc00.kongapiinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 *
 * @author tkzc00
 */
public class KongApiClient {
    private String accessKey;
    private String secretKey;

    public KongApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return HttpUtil.get("http://localhost:8081/api/name", paramMap);
    }

    public String getNameByPost(@RequestParam String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return HttpUtil.post("http://localhost:8081/api/name", paramMap);
    }

    public String getUserNameByPost(@RequestBody User user) {
        String json = JSONUtil.toJsonStr(user);
        return HttpRequest.post("http://localhost:8081/api/name/user")
                .addHeaders(getHeadersMap(json))
                .body(json).execute().body();
    }

    private Map<String, String> getHeadersMap(String body) {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("accessKey", accessKey);
//        headersMap.put("secretKey", secretKey);
        headersMap.put("nonce", RandomUtil.randomNumbers(4));
        headersMap.put("body", body);
        headersMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        headersMap.put("sign", SignUtils.getSign(body, secretKey));
        return headersMap;
    }
}
