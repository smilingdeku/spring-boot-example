package org.example.config.security.service;

import org.example.common.constant.MsgKeyConstant;
import org.example.common.exception.BusinessException;
import org.example.common.util.ConvertUtil;
import org.example.common.util.MessageUtil;
import org.example.module.system.user.domain.entity.SysUser;
import org.example.module.system.user.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(MessageUtil.message(MsgKeyConstant.SYSTEM_USER_NOT_EXISTED, username));
        }
        if (!ConvertUtil.toBoolean(user.getStatus(), false)) {
            throw new BusinessException(MessageUtil.message(MsgKeyConstant.SYSTEM_USER_IS_DISABLE, username));
        }
        String[] permissions = sysUserService.listPermissionByUsername(username).toArray(new String[]{});
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(permissions);
        return new User(user.getUsername(), user.getPassword(), authorityList);
    }
}
