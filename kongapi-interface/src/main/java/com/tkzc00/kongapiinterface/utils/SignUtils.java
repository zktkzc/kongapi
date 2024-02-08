package com.tkzc00.kongapiinterface.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * 签名工具
 *
 * @author tkzc00
 */
public class SignUtils {
    /**
     * 生成签名
     *
     * @param headersMap 请求头
     * @param secretKey  秘钥
     * @return 签名
     */
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;
        return md5.digestHex(content);
    }
}
