package com.duan.springboot.learning.excel.util.excel;

import com.duan.springboot.learning.excel.pojo.vo.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * @author duanjw
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ExcelSheetData {

    /**
     * sheet名称，默认为原sheet名称
     * 如果有第二个sheet的数据，但是没有第二个sheet，则名称为第一个sheet名称
     */
    private String sheetName ;

    /**
     * sheet页中存储 #{key} 的数据
     */
    private Map<String, Object> mapData = new HashMap<>();

    /**
     * sheet页中存储 ${key} 的数据
     */
    private Map<String, List<Object>> mapListData = new HashMap<>();

    /**
     * mapData添加数据
     *
     * @param object
     * @return
     */
    public ExcelSheetData addMapData(String key, Object object) {
        this.mapData.put(key, object);
        return this;
    }

    /**
     * mapData添加数据
     * 不设置mapData的key，key默认为传入类的名字
     *
     * @param object
     * @return
     */
    public ExcelSheetData addMapData(Object object) {
        String className = object.getClass().getSimpleName();
        this.mapData.put(className, object);
        return this;
    }

    /**
     * mapListData添加数据
     * @param mapListData
     * @return
     */
    public ExcelSheetData addMapListData(Map<String, List> mapListData) {
        mapListData.forEach((k,v) -> {
            this.mapListData.put(k,v);
        });
        return this;
    }

    /**
     * mapListData添加数据
     * 不设置mapListData的key，key默认为list中第一个对象的类的名字
     * @param listData
     * @return
     */
    public ExcelSheetData addMapListData(List listData) {
        String className = listData.get(0).getClass().getSimpleName();
        this.mapListData.put(className, listData);
        return this;
    }

    /**
     * mapListData添加数据
     * key为传入的key
     *
     * @param listData
     * @return
     */
    public ExcelSheetData addMapListData(String key, List listData) {
        this.mapListData.put(key, listData);
        return this;
    }

    public ExcelSheetData(String sheetName) {
        this.sheetName = sheetName;
    }

    public ExcelSheetData(String sheetName, List mapListData) {
        addMapListData(mapListData);
        this.sheetName = sheetName;
    }

    public ExcelSheetData(String sheetName,String key, List mapListData) {
        addMapListData(key, mapListData);
        this.sheetName = sheetName;
    }

    public ExcelSheetData(String sheetName, Map<String, List<Object>> mapListData) {
        this.mapListData = mapListData;
        this.sheetName = sheetName;
    }

    /**
     * 根据key获取key的值
     * key为 key或object.key
     * @param key
     * @return
     */
    public Object getByMapData(String key) {
        String [] keys = key.split("\\.");
        if(keys.length == 2 && keys[1] != null) {
            return CommonUtils.getValue(mapData.get(keys[0]), keys[1]);
        } else {
            return mapData.get(key);
        }
    }

    /**
     * 根据key获取key的值
     * key为 key或object.key
     * @param key
     * @return
     */
    public Object getByMapListData(String key) {
        String [] keys = key.split("\\.");
        if(keys.length == 2 && keys[1] != null) {
            return CommonUtils.getValue(mapData.get(keys[0]), keys[1]);
        } else {
            return mapListData.get(key);
        }
    }
}
