package com.okliu.list.util;

import net.sf.json.JSONArray;

/**
 * <p>Title: JsonUtils.java</p>
 * <p>Description: json工具类</p>
 * @author liuhaolie
 * @date 2019-03-03 14:44
 * @version 1.0
 */
public class JsonUtils {

    /**
     * 将任意java对象转换成json字符串串返回
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return JSONArray.fromObject(obj).toString();
    }
    
}
