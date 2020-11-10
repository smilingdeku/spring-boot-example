package org.example.config.security.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.example.common.domain.Result;
import org.example.common.constant.MsgKeyConstant;
import org.example.common.util.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationEntryPoint")
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JSON.toJSONString(Result.failure(
                MessageUtil.message(MsgKeyConstant.UNAUTHORIZED, request.getRequestURI())),
                SerializerFeature.WriteMapNullValue));
    }
}
