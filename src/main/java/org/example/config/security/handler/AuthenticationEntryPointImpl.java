package org.example.config.security.handler;


import org.example.common.constant.MsgKeyConstant;
import org.example.common.domain.response.Result;
import org.example.common.util.JsonUtil;
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
        response.getWriter().print(JsonUtil.toJSONString(Result.failure(
                MessageUtil.get(MsgKeyConstant.UNAUTHORIZED, request.getRequestURI()))));
    }
}
