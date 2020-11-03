package org.example.common.base;

import org.example.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linzhaoming
 * @since 2020/11/03
 **/
public class BaseController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    protected String getCurrentUsername() {
        String token = jwtTokenUtil.getToken(request);
        return jwtTokenUtil.getSubjectByToken(token);
    }

}
