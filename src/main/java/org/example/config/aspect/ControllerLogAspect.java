package org.example.config.aspect;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version V1.0
 * @since 2020-11-21 15:05
 */
@Aspect
@Component
public class ControllerLogAspect {

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
            Map<String, Object> argMap = dealWithArgs(parameterNames, joinPoint.getArgs());
            request = JsonUtil.toJSONString(argMap);

            result = joinPoint.proceed();

            long end = System.currentTimeMillis();
            log.info("Function[{}] spend: {} ms, request: {}, response: {}", methodFingerPrint, (end - start), request,
                JsonUtil.toJSONString(result));
        } catch (Throwable th) {
            log.error("Function[" + methodFingerPrint + "] failure, request:" + request, th);
            throw th;
        }
        return result;
    }

    private Map<String, Object> dealWithArgs(String[] parameters, Object[] args) {
        if (Objects.isNull(args) || args.length == 0) {
            return null;
        }

        Map<String, Object> valueMap = new HashMap<>(16);
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

            valueMap.put(parameters[index], arg);
        }
        return valueMap;
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
