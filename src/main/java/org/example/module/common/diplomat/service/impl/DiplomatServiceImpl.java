package org.example.module.common.diplomat.service.impl;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.example.common.constant.MsgKeyConstant;
import org.example.common.exception.BusinessException;
import org.example.common.util.JsonUtil;
import org.example.common.util.MessageUtil;
import org.example.module.common.diplomat.service.IDiplomatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author walle
 * @version V1.0
 * @since 2020-12-10 21:47
 */
@Service
public class DiplomatServiceImpl implements IDiplomatService {

    private static Logger logger = LoggerFactory.getLogger(DiplomatServiceImpl.class);

    @Resource
    private RestTemplate restTemplate;

    @Override
    public <T> T post(String url, Object requestBody, Class<T> valueClazz) {
        String requestBodyJsonStr = JsonUtil.toJSONString(requestBody);
        if (logger.isDebugEnabled()) {
            logger.info("Func[post] url: {},params: {}", url, requestBodyJsonStr);
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
                logger.info("Func[post] url: {},params: {}, result: {}", url, requestBodyJsonStr,
                    JsonUtil.toJSONString(value));
            }

            return value;
        } catch (Exception e) {
            logger.error("Func[post] url: " + url + " ,params: " + requestBodyJsonStr, e);
            String tips = MessageUtil.get(MsgKeyConstant.RPC_FAILURE);
            throw new BusinessException(tips);
        }
    }

    @Override
    public <T> T get(String url, Class<T> valueClazz, Map<String, Object> params) {
        String requestJsonStr = JsonUtil.toJSONString(params);
        if (logger.isDebugEnabled()) {
            logger.info("Func[get] url: {},params: {}", url, requestJsonStr);
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
                logger.info("Func[get] url: {},params: {}, result: {}", url, requestJsonStr,
                    JsonUtil.toJSONString(value));
            }
            return value;
        } catch (Exception e) {
            logger.error("Func[get] url: " + url + " ,params: " + requestJsonStr, e);
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
    private boolean isRpcFailure(ResponseEntity<?> rpcResult) {
        if (Objects.isNull(rpcResult)) {
            return true;
        }
        return rpcResult.getStatusCodeValue() != HttpStatus.OK.value();
    }

}
