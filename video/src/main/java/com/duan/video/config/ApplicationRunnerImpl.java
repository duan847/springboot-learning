package com.duan.video.config;

import com.duan.video.common.Constants;
import com.duan.video.pojo.entity.Dict;
import com.duan.video.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 程序启动成功后执行
 * 1. 加载数据字典
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private DictService dictService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Dict> dictList = dictService.list();
        Map<String,Integer> dictMap = new HashMap<>(dictList.size());
        for (Dict dict :  dictList) {
            dictMap.put(dict.getPid() + ":" + dict.getValue(), dict.getId());
        }
        Constants.dictMap = dictMap;
    }
}
