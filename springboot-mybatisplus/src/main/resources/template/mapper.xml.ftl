<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#channels table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#channels>
<#channels table.commonFields as field><#--生成公共字段 -->
    <result column="${field.name}" property="${field.propertyName}" />
</#channels>
<#channels table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>
</#channels>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
<#channels table.commonFields as field>
        ${field.name},
</#channels>
        ${table.fieldNames}
    </sql>

</#if>
    <!-- 分页查询${table.comment} -->
    <select id="selectPage" resultType="${package.Entity}.${entity}">
        select * from ${entity}
        <trim prefix="where" prefixOverrides="AND">
            <if test="params.name != null and '' != params.name">
                <bind name="name_like" value="'%' + params.name + '%'" />
                AND name LIKE <#noparse>#{</#noparse>name_like}
            </if>
        </trim>
    </select>

</mapper>
