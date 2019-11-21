package com.yh.java8.utils;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

    private static final String SECRET_KEY = "ThisIsASecretKeyThisIsASecretKey";

    /**
     * JWT分为头部、载荷、签名
     *
     * @param payloadMap 需要加密的数据
     * @return 生成的JWT
     * @throws Exception
     */
    public static String createToken(Map<String, Object> payloadMap) throws Exception {
        //创建头部，指定算法
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(SECRET_KEY);

        //签名
        jwsObject.sign(jwsSigner);

        //生成token
        return jwsObject.serialize();
    }

    public static Map<String, Object> valid(String token) throws Exception {

        //解析token
        JWSObject jwsObject = JWSObject.parse(token);

        //获取到载荷
        Payload payload = jwsObject.getPayload();

        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(SECRET_KEY);

        Map<String, Object> resultMap = new HashMap<>();
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            resultMap.put("Result", 0);
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put("data", jsonObject);

            //判断token是否过期
            if (jsonObject.containsKey("exp")) {
                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
                Long nowTime = System.currentTimeMillis();
                //判断是否过期
                if (nowTime > expTime) {
                    //已经过期
                    resultMap.clear();
                    resultMap.put("Result", 2);
                }
            }
        } else {
            resultMap.put("Result", 1);
        }
        return resultMap;
    }

}
