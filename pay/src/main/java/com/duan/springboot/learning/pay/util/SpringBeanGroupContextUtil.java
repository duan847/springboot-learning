package com.duan.springboot.learning.pay.util;


import com.duan.springboot.learning.pay.annotate.SpringBeanGroup;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.duan.springboot.learning.pay.annotate.SpringBeanGroup.SPLIT_STR;

/**
 * spring 分组后的bean 工具类
 *
 * - 可以根据自定义的分组和名称获取spring bean
 * - 可以根据默认分组和名称获取spring bean
 * 如：@SpringBeanGroup("bean")，可以使用SpringBeanGroupContextUtil.getBean("bean")获取到bean
 * 如：@SpringBeanGroup(group = "testGroup", value = "test")，可以使用SpringBeanGroupContextUtil.getBean("testGroup", "test")获取到bean
 *
 * @author duanjw
 */
@Component
public class SpringBeanGroupContextUtil implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    /**
     * 根据value获取分组的bean
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T getBean(String value) {
        Class clazz = beanGroupMap.get(SpringBeanGroup.DEFAULT_GROUP + value);
        return getBean(clazz);
    }

    /**
     * 根据分组和value获取分组的bean
     * @param groupName
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String groupName, String name) {
        Class clazz = beanGroupMap.get(groupName + SPLIT_STR + name);
        return getBean(clazz);
    }

    /**
     * 获取spring applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * spring bean初始完毕后，找出带有SpringClassGroup注解的bean，放到beanGroupMap中
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 根容器为Spring容器
        if (event.getApplicationContext().getParent() == null) {
            Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(SpringBeanGroup.class);
            for (Object bean : beans.values()) {
                SpringBeanGroup annotation = bean.getClass().getAnnotation(SpringBeanGroup.class);
                String groupName = annotation.group();
                //如果分组名称最后字符不是":"号，添加":"号
                if(!groupName.endsWith(SPLIT_STR)) {
                    groupName = groupName + SPLIT_STR;
                }
                beanGroupMap.put(groupName + annotation.value(), bean.getClass());
            }
        }
    }

    /**
     * 设置spring applicationContext
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanGroupContextUtil.applicationContext = applicationContext;
    }



    /**
     * 分组后的bean
     */
    private static Map<String, Class> beanGroupMap = new HashMap();

    private static ApplicationContext applicationContext;

    private static <T> T getBean(Class clazz) {
        return (T)applicationContext.getBean(clazz);
    }

}
