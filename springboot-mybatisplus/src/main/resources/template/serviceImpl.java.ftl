package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.duan.springboot.learning.mybatisplus.common.Query;
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Autowired
    public ${table.mapperName} ${table.entityPath}Mapper;

    /**
     * 分页查询${table.comment}
     *
     * @param query
     * @return
     */
    @Override
    public IPage<${entity}> selectPage(Query<${entity}> query) {
      return query.setRecords(${table.entityPath}Mapper.selectPage(query));
    }

}
</#if>
