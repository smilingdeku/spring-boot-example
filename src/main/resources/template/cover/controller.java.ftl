package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ${cfg.logAnnotationClass};
import ${cfg.queryRequestClass};
import ${cfg.pageResultClass};
import ${cfg.resultClass};
import ${cfg.mapperUtilClass};
import ${cfg.requestPackage}.${entity}Request;
import ${cfg.responsePackage}.${entity}Response;
import ${package.ServiceImpl}.${table.serviceImplName};
import ${package.Mapper}.${table.mapperName};
import ${package.Entity}.${entity};
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceImplName}, ${table.mapperName}, ${entity}> {
<#else>
public class ${table.controllerName} extends ${superControllerClass}<${table.serviceImplName}, ${table.mapperName}, ${entity}> {
</#if>

    @GetMapping("/page")
    public PageResult<${entity}Response> page(@RequestParam Map<String, Object> requestParam) {
        QueryRequest query = QueryRequest.from(requestParam);
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
        // if (!StringUtils.isEmpty(query.getKeyword())) {
        //      queryWrapper.like(${entity}::getName, query.getKeyword());
        // }
        IPage<${entity}> page = getService()
                .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);

        List<${entity}Response> responseList = MapperUtil.mapList(page.getRecords(), ${entity}.class, ${entity}Response.class);

        return PageResult.build(responseList, page.getTotal());
    }

    @GetMapping("/{id}")
    public Result<${entity}Response> get(@PathVariable Long id) {
        ${entity} target = getService().getById(id);
        ${entity}Response response = MapperUtil.map(target, ${entity}Response.class);
        return Result.success(response);
    }

    @Log
    @PostMapping
    public Result<${entity}Response> save(@RequestBody ${entity} record) {
        boolean success = getService().save(record);
        ${entity}Response response = MapperUtil.map(record, ${entity}Response.class);
        return success ? Result.success(response) : Result.failure();
    }

    @Log
    @PutMapping
    public Result<${entity}Response> update(@RequestBody ${entity} record) {
        boolean success = getService().updateById(record);
        ${entity}Response response = MapperUtil.map(record, ${entity}Response.class);
        return success ? Result.success(response) : Result.failure();
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        getService().removeByIds(idList);
        return Result.success();
    }

}
</#if>
