package com.duan.springboot.learning.excel.util.excel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * sheet页数据定制
 * @author duanjw
 *
 */
public class SheetData  {

    /**
     * sheet页中存储 #{key} 的数据
     */
    private Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 列表数据存储 sheet页中替换${key} 并以列为单位向下赋值
     */
    private List<Object> datas = new LinkedList<Object>();

    private String name ;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    public SheetData(String name) {
        super();
        this.name = name;
    }

    public SheetData(String name, List<Object> listData) {
        super();
        this.name = name;
        this.datas = listData;
    }

    public SheetData(String name, List<Object> listData, Map<String, Object> mapData ) {
        super();
        this.name = name;
        this.datas = listData;
        this.map = mapData;
    }

    public void put(String key , Object value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public Object get(String key) {
        return map.get(key);
    }

    /**
     * 清理map存储和数据存储
     */
    public void clear() {
        map.clear();
        datas.clear();
    }

    public void addData(Object t){
        datas.add(t);
    }

    public void addDatas(List<? extends Object> list) {
        datas.addAll(list);
    }


    public List<Object>  getDatas() {
        return datas;
    }

}
