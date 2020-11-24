package org.example.config.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.common.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author huapeng.huang
 * @version V1.0
 * @since 2020-11-21 15:05
 */
@Aspect
@Component
public class WatchdogInterceptor {

    private static final String ASPECT_CONTROLLER_EXP = "execution(* org.example.module.*.*.controller..*(..))";

    @Around(ASPECT_CONTROLLER_EXP)
    public Object controller(ProceedingJoinPoint joinPoint) throws Throwable {
        return execute(joinPoint);
    }

    private Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object result;
        String request = null;
        String methodFingerPrint = buildMethodFingerPrint(signature);
        Logger log = LoggerFactory.getLogger(signature.getDeclaringType());
        try {
            long start = System.currentTimeMillis();

            String[] parameterNames = signature.getParameterNames();
            List<Object> args = dealWithArgs(parameterNames, joinPoint.getArgs());
            request = JSONUtil.toJSONString(args);

            result = joinPoint.proceed();

            long end = System.currentTimeMillis();
            log.info("function[{}] spend: {}ms, request: {}, response: {}", methodFingerPrint, (end - start), request,
                JSONUtil.toJSONString(result));
        } catch (Throwable th) {
            log.error("function[" + methodFingerPrint + "] failure, request:" + request, th);
            throw th;
        }
        return result;
    }

    private List<Object> dealWithArgs(String[] parameters, Object[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            return null;
        }
        List<Object> values = new ArrayList<>();
        for (int index = 0, len = args.length; index < len; index++) {
            Object arg = args[index];
            if (arg instanceof MultipartFile) {
                continue;
            }
            if (arg instanceof HttpServletRequest) {
                continue;
            }
            if (arg instanceof HttpServletResponse) {
                continue;
            }

            HashMap<Object, Object> valueMap = new HashMap<>(4);
            valueMap.put(parameters[index], arg);

            values.add(valueMap);
        }
        return values;
    }

    private String buildMethodFingerPrint(MethodSignature signature) {
        //        Class<?> clazz = signature.getDeclaringType();
        //        String className = clazz.getName();
        //
        //        int index = className.lastIndexOf(".");
        //        String lastClassName = className.substring(index + 1);

        return signature.getName();
    }

}
