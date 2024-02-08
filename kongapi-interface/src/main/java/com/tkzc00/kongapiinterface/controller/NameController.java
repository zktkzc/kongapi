package com.tkzc00.kongapiinterface.controller;

import com.tkzc00.kongapiinterface.model.User;
import com.tkzc00.kongapiinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 *
 * @author tkzc00
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping
    public String getNameByGet(String name) {
        return "GET 你的名字是：" + name;
    }

    @PostMapping
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String body = request.getHeader("body");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        // 实际情况是从数据库中查是否分配给用户
        if (!accessKey.equals("tkzc00"))
            throw new RuntimeException("无权限");
        if (Long.parseLong(nonce) > 10000)
            throw new RuntimeException("nonce不合法");
        if (System.currentTimeMillis() / 1000 - Long.parseLong(timestamp) > 10000)
            throw new RuntimeException("过期");
        // 实际情况中是从数据库中查出secretKey
        String sign1 = SignUtils.getSign(body, "abcdefgh");
        if (!sign.equals(sign1))
            throw new RuntimeException("签名错误");
        return "POST 你的用户名是：" + user.getUsername();
    }
}
