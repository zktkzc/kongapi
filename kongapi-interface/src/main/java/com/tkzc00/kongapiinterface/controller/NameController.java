package com.tkzc00.kongapiinterface.controller;

import com.tkzc00.kongapiinterface.model.User;
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
        String secretKey = request.getHeader("secretKey");
        if (!accessKey.equals("tkzc00") || !secretKey.equals("abcdefgh"))
            throw new RuntimeException("无权限");
        return "POST 你的用户名是：" + user.getUsername();
    }
}
