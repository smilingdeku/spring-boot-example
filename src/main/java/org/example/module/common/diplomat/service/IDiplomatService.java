package org.example.module.common.diplomat.service;

import java.util.Map;

/**
 * @author walle
 * @version V1.0
 * @since 2020-12-10 21:47
 */
public interface IDiplomatService {

    /**
     * post请求
     *
     * @param url         url地址
     * @param requestBody 请求参数
     * @param valueClazz  返回结果对象class
     * @return T
     */
    <T> T post(String url, Object requestBody, Class<T> valueClazz);

    /**
     * get请求
     *
     * @param url        url地址
     * @param valueClazz 返回结果class对象
     * @param params     请求参数
     * @return T
     */
    <T> T get(String url, Class<T> valueClazz, Map<String, Object> params);
}
