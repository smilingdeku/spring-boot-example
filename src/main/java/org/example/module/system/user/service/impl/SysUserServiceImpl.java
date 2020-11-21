package org.example.module.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.base.BaseService;
import org.example.common.util.JwtTokenUtil;
import org.example.module.system.user.domain.entity.SysUser;
import org.example.module.system.user.domain.request.SysUserRequest;
import org.example.module.system.user.mapper.SysUserMapper;
import org.example.module.system.user.service.ISysUserService;
import org.example.module.system.userrole.domain.entity.SysUserRole;
import org.example.module.system.userrole.service.ISysUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author linzhaoming
 * @since 2020-10-23
 */
@Service
public class SysUserServiceImpl extends BaseService<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Override
    public SysUser getUserByUsername(String username) {
        return this.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username));
    }

    @Override
    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenUtil.generateToken(username);
    }

    @Override
    public SysUser getByUsername(String username) {
        return this.getOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username));
    }

    @Override
    public List<String> listPermissionByUsername(String username) {
        return this.getBaseMapper().listPermissionByUsername(username);
    }

    @Transactional
    @Override
    public SysUser saveUserAndRoles(SysUserRequest request) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(request, sysUser);
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        this.save(sysUser);
        if (!CollectionUtils.isEmpty(request.getRoleIds())) {
            request.getRoleIds().forEach(roleId -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(sysUser.getId());
                userRole.setRoleId(roleId);
                sysUserRoleService.save(userRole);
            });
        }
        return sysUser;
    }

    @Transactional
    @Override
    public SysUser updateUserAndRoles(SysUserRequest request) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(request, sysUser);
        SysUser old = getById(request.getId());
        if (Objects.nonNull(old) && !old.getPassword().equals(sysUser.getPassword())) {
            String newPassword = passwordEncoder.encode(sysUser.getPassword());
            sysUser.setPassword(newPassword);
        }
        this.updateById(sysUser);
        List<SysUserRole> userRoleList = sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, sysUser.getId()));
        userRoleList.stream().filter(item -> !request.getRoleIds().contains(item.getRoleId())).forEach(item -> {
            sysUserRoleService.removeById(item);
            request.getRoleIds().remove(item.getRoleId());
        });
        if (!CollectionUtils.isEmpty(request.getRoleIds())) {
            request.getRoleIds().forEach(roleId -> {
                if (userRoleList.stream().noneMatch(item -> item.getRoleId().equals(roleId))) {
                    SysUserRole userRole = new SysUserRole();
                    userRole.setUserId(sysUser.getId());
                    userRole.setRoleId(roleId);
                    sysUserRoleService.save(userRole);
                }
            });
        }
        return sysUser;
    }

}
