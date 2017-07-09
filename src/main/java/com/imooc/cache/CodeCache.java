package com.imooc.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证码缓存
 */
public class CodeCache {

    private Map<Long,String> codeMap;

    private CodeCache(){
        codeMap=new HashMap<Long, String>();
    }

    /**
     * 保存手机号与验证码
     */
    public boolean save(Long phone,String code) {
        if(codeMap.containsKey(phone)) {
            return false;
        }
        codeMap.put(phone, code);
        return true;
    }

    /**
     * 根据手机号获取验证码
     */
    public String getCode(Long phone) {
        return codeMap.get(phone);
    }

    public static CodeCache getInstance() {
        return CodeCacheInternal.codeCache;
    }

        private static class CodeCacheInternal{

        private static CodeCache codeCache=new CodeCache();

    }
}
