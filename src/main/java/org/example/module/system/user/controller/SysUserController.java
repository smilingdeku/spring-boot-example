package org.example.module.system.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.annotation.Log;
import org.example.common.base.BaseController;
import org.example.common.domain.request.QueryRequest;
import org.example.common.domain.response.PageResult;
import org.example.common.domain.response.Result;
import org.example.common.util.ListUtil;
import org.example.common.util.MapperUtil;
import org.example.module.system.user.domain.entity.SysUser;
import org.example.module.system.user.domain.request.LoginRequest;
import org.example.module.system.user.domain.request.SysUserRequest;
import org.example.module.system.user.domain.response.LoginResponse;
import org.example.module.system.user.domain.response.SysUserResponse;
import org.example.module.system.user.domain.response.UserResponse;
import org.example.module.system.user.mapper.SysUserMapper;
import org.example.module.system.user.service.impl.SysUserServiceImpl;
import org.example.module.system.userrole.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author linzhaoming
 * @since 2020-10-23
 */
@Api(tags = {"系统用户接口"})
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUserServiceImpl, SysUserMapper, SysUser> {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Log
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        String token = getService()
                .login(request.getUsername(), request.getPassword(), request.getCaptchaKey(), request.getCaptcha());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return Result.success(response);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping
    public Result<UserResponse> info() {
        SysUser user = getService().getByUsername(getCurrentUsername());
        UserResponse response = MapperUtil.map(user, UserResponse.class);
        response.setPermissions(getService().listPermissionByUsername(getCurrentUsername()));
        return Result.success(response);
    }

    @ApiOperation(value = "获取用户分页数据")
    @PreAuthorize("hasAuthority('system:user')")
    @GetMapping("/page")
    public PageResult<SysUserResponse> page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = QueryRequest.from(requestParam);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            queryWrapper.lambda().like(SysUser::getUsername, query.getKeyword());
        }
        if (!StringUtils.isEmpty(query.getLineOrderField())) {
            queryWrapper.orderBy(true, query.getIsAsc(), query.getLineOrderField());
        }
        IPage<SysUser> page = getService()
            .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);
        List<SysUserResponse> responseList = MapperUtil.mapList(page.getRecords(), SysUser.class, SysUserResponse.class);
        return PageResult.build(responseList, page.getTotal());
    }

    @ApiOperation(value = "获取用户信息")
    @PreAuthorize("hasAuthority('system:user:edit')")
    @GetMapping("/{id}")
    public Result<SysUserResponse> get(@PathVariable Long id) {
        SysUser sysUser = getService().getById(id);
        SysUserResponse response = MapperUtil.map(sysUser, SysUserResponse.class);
        return Result.success(response);
    }

    @ApiOperation(value = "添加用户")
    @Log
    @PreAuthorize("hasAuthority('system:user:add')")
    @PostMapping
    public Result<SysUserResponse> save(@RequestBody SysUserRequest request) {
        SysUser sysUser = getService().saveUserAndRoles(request);
        SysUserResponse response = MapperUtil.map(sysUser, SysUserResponse.class);
        return Result.success(response);
    }

    @ApiOperation(value = "删除用户")
    @Log
    @PreAuthorize("hasAuthority('system:user:delete')")
    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable Long[] ids) {
        // 去除admin
        List<Long> idList = ListUtil.arr2List(ids);
        ListUtil.removeIf(idList, (e) -> e.equals(1L));

        if (ListUtil.isNotEmpty(idList)) {
            idList.forEach(getService()::deleteUserAndRoles);
        }
        return Result.success();
    }

    @ApiOperation(value = "更新用户")
    @Log
    @PreAuthorize("hasAuthority('system:user:edit')")
    @PutMapping
    public Result<SysUserResponse> update(@RequestBody SysUserRequest request) {
        SysUser sysUser = getService().updateUserAndRoles(request);
        SysUserResponse response = MapperUtil.map(sysUser, SysUserResponse.class);
        return Result.success(response);
    }

    @ApiOperation(value = "获取用户角色 ID 列表")
    @PreAuthorize("hasAnyAuthority('system:user:add', 'system:user:edit')")
    @GetMapping("/{id}/roles")
    public Result<List<Long>> userRoles(@PathVariable Long id) {
        List<Long> roleIds = sysUserRoleService.listRoleIdByUserId(id);
        return Result.success(roleIds);
    }

}
