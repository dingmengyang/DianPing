package com.imooc.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证码缓存
 */
public class TokenCache {

    private Map<Long,String> tokenMap;

    private TokenCache(){
        tokenMap =new HashMap<Long, String>();
    }

    /**
     * 保存手机号token
     */
    public boolean save(Long phone,String token) {
        if(tokenMap.containsKey(phone)) {
            return false;
        }
        tokenMap.put(phone, token);
        return true;
    }

    /**
     * 根据手机号获取token
     */
    public String getToken(Long phone) {
        return tokenMap.get(phone);
    }

    /**
     * 根据手机号获取token
     */
    public Long getPhone(String token) {
        for (Map.Entry<Long, String> entry : tokenMap.entrySet()) {
            if (entry.getValue().equals(token)){
                return entry.getKey();
            }
        }
        return null;
    }

    public static TokenCache getInstance() {
        return CodeCacheInternal.tokenCache;
    }

        private static class CodeCacheInternal{

        private static TokenCache tokenCache =new TokenCache();

    }
}
