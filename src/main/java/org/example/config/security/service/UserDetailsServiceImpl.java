package org.example.config.security.service;

import org.example.config.security.domain.User;
import org.example.constant.MsgKeyConstant;
import org.example.exception.BusinessException;
import org.example.module.system.user.domain.SysUser;
import org.example.module.system.user.service.ISysUserService;
import org.example.util.ConvertUtil;
import org.example.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getUserByUserName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(MessageUtil.message(MsgKeyConstant.SYSTEM_USER_NOT_EXISTED, username));
        }
        if (Objects.nonNull(user.getDeletedAt())) {
            throw new BusinessException(MessageUtil.message(MsgKeyConstant.SYSTEM_USER_IS_DELETED, username));
        }
        if (!ConvertUtil.getAsBoolean(user.getStatus(), false)) {
            throw new BusinessException(MessageUtil.message(MsgKeyConstant.SYSTEM_USER_IS_DISABLE, username));
        }
        return new User(user, Collections.emptySet());
    }
}
