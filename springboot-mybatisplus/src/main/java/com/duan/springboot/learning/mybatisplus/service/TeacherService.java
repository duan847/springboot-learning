package com.duan.springboot.learning.mybatisplus.service;

import com.duan.springboot.learning.mybatisplus.pojo.entity.Teacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.springboot.learning.mybatisplus.common.Query;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author duanjw
 * @since 2019-06-04
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 分页查询
     *
     * @param query 分页对象
     * @return 分页对象
     */
    Page<Teacher> selectPage(Query<Teacher> query);

}
