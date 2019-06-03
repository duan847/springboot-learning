package ${package.Mapper};

import ${package.Entity}.${entity};
import com.duan.springboot.learning.mybatisplus.common.Query;
import ${superMapperClassPackage};

import java.util.List;
/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    /**
     * 分页查询${table.comment}
     *
     * @param query 分页对象
     * @return List数组
     */
    List<${entity}> selectPage(Query<${entity}> query);

}
</#if>
