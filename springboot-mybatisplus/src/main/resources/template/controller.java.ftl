package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.springboot.learning.common.R;
import com.duan.springboot.learning.mybatisplus.common.Query;
import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package.Entity}.${entity};
import ${package.Service}.${entity}Service;

import java.util.Map;
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
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${entity}Service ${table.entityPath}Service;

    /**
     * 新增${table.comment}
     * @param  ${table.entityPath}  实体
     * @return success/false
     */
    @PostMapping
    public R<Boolean> save(@RequestBody ${entity} ${table.entityPath}) {
        return R.ok(${table.entityPath}Service.save(${table.entityPath}));
    }

    /**
     * 根据id查看${table.comment}
     *
     * @param id ID
     * @return ${entity}
     */
    @GetMapping("/{id}")
    public R<${entity}> getById(@PathVariable Long id) {
        return R.ok(${table.entityPath}Service.getById(id));
    }

    /**
     * 分页查询${table.comment}
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Page page(@RequestParam Map<String, Object> params) {
        return ${table.entityPath}Service.selectPage(new Query(params));
    }

    /**
     * 根据id删除${table.comment}
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public R<Boolean> removeById(@PathVariable Long id) {
       return R.ok(${table.entityPath}Service.removeById(id));
    }

    /**
     * 根据id修改${table.comment}
     * @param  ${table.entityPath}  实体
     * @return success/false
     */
    @PutMapping
    public R<Boolean> updateById(@RequestBody ${entity} ${table.entityPath}) {
        return R.ok(${table.entityPath}Service.updateById(${table.entityPath}));
    }
}
</#if>
