package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ${cfg.logAnnotationClass};
import ${cfg.queryRequestClass};
import ${cfg.resultClass};
import ${package.ServiceImpl}.${table.serviceImplName};
import ${package.Mapper}.${table.mapperName};
import ${package.Entity}.${entity};
import org.springframework.util.StringUtils;
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
     public Result page(@RequestParam Map<String , Object> requestParam) {
         QueryRequest query = mapToQuery(requestParam);
         LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
         if (!StringUtils.isEmpty(query.getKeyword())) {
          queryWrapper.like(${entity}::getName, query.getKeyword());
         }
         IPage<${entity}> page = getService()
           .page(new Page<>(query.getPageIndex(), query.getPageSize()), queryWrapper);

         return Result.success(page);
      }

     @GetMapping("/{id}")
     public Result get(@PathVariable Long id) {
         return Result.success(getService().getById(id));
     }

     @Log
     @PostMapping
     public Result save(@RequestBody ${entity} record) {
         boolean success = getService().save(record);
         return success ? Result.success(record) : Result.failure();
     }

     @Log
     @PutMapping
     public Result update(@RequestBody  ${entity} record) {
         boolean success = getService().updateById(record);
         return success ? Result.success(record) : Result.failure();
     }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable Long[] ids) {
    List<Long> idList = Arrays.asList(ids);
        getService().removeByIds(idList);
        return Result.success();
    }

}
</#if>
