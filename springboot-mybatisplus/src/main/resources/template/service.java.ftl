package ${package.Service};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.springboot.learning.mybatisplus.common.Query;
import ${superServiceClassPackage};

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 分页查询${table.comment}
     *
     * @param query 分页对象
     * @return 分页对象
     */
    Page<${entity}> selectPage(Query<${entity}> query);

}
</#if>
