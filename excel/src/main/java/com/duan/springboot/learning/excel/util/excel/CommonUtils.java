package com.duan.springboot.learning.excel.util.excel;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Slf4j
public class CommonUtils {
    /**
     * 从对象中取出属性的值
     * @param object pojo或者map
     * @param field 属性名称
     * @return object
     */

    public static Object getValue(Object object , String field) {
        if(object instanceof Map) {
            Map map = (Map) object;
            return map.get(field);
        }
        try {
            String method = "get" + field.substring(0 , 1).toUpperCase() + field.substring(1);
            Method m = object.getClass().getMethod(method);
            if(m != null) {
                return m.invoke(object);
            }

        } catch (NoSuchMethodException e) {
            log.info("对象：{}没有：{}属性的get方法", object, field);
        }
        catch (Exception e) {
            log.error("获取对象：{}的属性：{}异常", object, field);
            return null;
        }


        return null ;

    }

    /**
     * 判断是否为数字
     * @param v
     * @return
     */
    public static boolean isNumber(Object v) {

        if(v == null) return false;

        if(v instanceof Number) {
            return true ;
        } else if(v.toString().matches("^\\d+$")) {
            return true ;
        } else if(v.toString().matches("^-?\\d+\\.?\\d+$")) {
            return true ;
        } else {
            try{
                Double.parseDouble(v.toString());
                return true ;
            }catch(Exception e) {
                return false;
            }
        }
    }

    /**
     * 返回 #{} 或者 ${} 中包含的值
     * @param str
     * @return eg:#{name} ${ages}
     */
    public static String[] getWildcard(String str ) {

        List<String> list = new ArrayList<String>();

        int start = 0;
        while(start < str.length() && start >= 0) {

            start = str.indexOf("{", start);

            int end = str.indexOf("}", start);
            if(start > 0) {
                String wc = str.substring(start - 1 , end + 1);

                list.add(wc);
            }

            if(start < 0) break ;

            start = end + 1;

        }

        return list.toArray(new String[0]);

    }
}
