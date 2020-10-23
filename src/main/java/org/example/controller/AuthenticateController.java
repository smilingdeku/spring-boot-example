package org.example.controller;

import org.example.common.LoginRequest;
import org.example.common.LoginResponse;
import org.example.common.ResultData;
import org.example.common.Code;
import org.example.common.TokenResponse;
import org.example.constant.SecurityConstants;
import org.example.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/api/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = new LoginResponse();
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authentication);
        String token = jwtTokenUtil.generateToken(request.getUsername());
        response.setToken(token);
        return ResponseEntity.ok(new ResultData<>(Code.SUCCESS, response));
    }

    @PostMapping("/api/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (StringUtils.isEmpty(token)) {
            throw new IllegalArgumentException("No token in request header");
        }
        String refreshToken = jwtTokenUtil.refreshToken(token);
        TokenResponse response = new TokenResponse();
        response.setToken(refreshToken);
        return ResponseEntity.ok(new ResultData<>(Code.SUCCESS, token));
    }

}
