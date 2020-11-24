package org.example.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * JSON 工具类
 *
 * @author linzhaoming
 * @since 2020/11/24
 **/
public class JSONUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    public static <T>T parseObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public static String toJSONString(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            return "";
        }
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
