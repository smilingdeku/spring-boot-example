package org.example.common.util;

import java.util.Map;
import java.util.Objects;

import org.example.common.constant.MsgKeyConstant;
import org.example.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Http 工具类
 *
 * @author walle@eva
 * @version V1.0
 * @since 2020-12-10 21:47
 */
@Component
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static RestTemplate restTemplate;

    @Autowired
    public HttpUtil(RestTemplate template) {
        restTemplate = template;
    }

    /**
     * post请求
     *
     * @param url         url
     * @param requestBody 请求参数
     * @param valueClazz  返回值类型
     * @return T
     */
    public static <T> T post(String url, Object requestBody, Class<T> valueClazz) {
        String requestBodyJsonStr = JsonUtil.toJSONString(requestBody);
        if (logger.isDebugEnabled()) {
            logger.info("Func[post] url: {}, params: {}", url, requestBodyJsonStr);
        }
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(requestBodyJsonStr, httpHeaders);

            ResponseEntity<T> rpcResult = restTemplate.postForEntity(url, request, valueClazz);
            if (isRpcFailure(rpcResult)) {
                throw new RuntimeException(rpcResult.toString());
            }

            T value = rpcResult.getBody();

            if (logger.isDebugEnabled()) {
                logger.info("Func[post] url: {}, params: {}, result: {}", url, requestBodyJsonStr,
                    JsonUtil.toJSONString(value));
            }

            return value;
        } catch (Exception e) {
            logger.error("Func[post] url: " + url + ", params: " + requestBodyJsonStr, e);
            String tips = MessageUtil.get(MsgKeyConstant.RPC_FAILURE);
            throw new BusinessException(tips);
        }
    }

    /**
     * Get请求
     *
     * @param url        url地址
     * @param valueClazz 返回参数类型
     * @param params     请求参数
     * @return T
     */
    public static <T> T get(String url, Class<T> valueClazz, Map<String, Object> params) {
        String requestJsonStr = JsonUtil.toJSONString(params);
        if (logger.isDebugEnabled()) {
            logger.info("Func[get] url: {}, params: {}", url, requestJsonStr);
        }
        try {
            // 请求接口
            ResponseEntity<T> rpcResult = restTemplate.getForEntity(url, valueClazz, params);

            // 判断是否请求成功
            if (isRpcFailure(rpcResult)) {
                throw new RuntimeException(rpcResult.toString());
            }

            T value = rpcResult.getBody();

            if (logger.isDebugEnabled()) {
                logger.info("Func[get] url: {}, params: {}, result: {}", url, requestJsonStr,
                    JsonUtil.toJSONString(value));
            }
            return value;
        } catch (Exception e) {
            logger.error("Func[get] url: " + url + ", params: " + requestJsonStr, e);
            String tips = MessageUtil.get(MsgKeyConstant.RPC_FAILURE);
            throw new BusinessException(tips);
        }
    }

    /**
     * 判断rpc调用是否失败
     *
     * @param rpcResult 调用结果
     * @return boolean
     */
    private static boolean isRpcFailure(ResponseEntity<?> rpcResult) {
        if (Objects.isNull(rpcResult)) {
            return true;
        }
        return rpcResult.getStatusCodeValue() != HttpStatus.OK.value();
    }
}
