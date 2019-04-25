package com.duan.springboot.learning.excel.controller;

import com.duan.springboot.learning.excel.pojo.vo.Family;
import com.duan.springboot.learning.excel.pojo.vo.Person;
import com.duan.springboot.learning.excel.pojo.vo.Teach;
import com.duan.springboot.learning.excel.util.excel.ExcelSheetData;
import com.duan.springboot.learning.excel.util.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 人员controller接口
 * 1. 导出学生列表excel
 * 2. 导入学生类标excel
 * 3. 导出学生制式+列表excel
 *
 * @author duanjw
 */
@RestController
@RequestMapping("person")
@Slf4j
public class PersonController {

    @GetMapping("export2")
    public void export(HttpServletResponse response) {

        //家庭成员数据
        List<Family> familyList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            familyList.add(new Family(i,"王胖子" + i, 1, i + 1 , "北京唐家岭东路", "18630070626"));
        }
        //教育情况数据
        List<Teach> teachList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            teachList.add(new Teach(i,"北京第" + i + "大学", "计算机" + i + "系", "本科", new Date(), new Date()));
        }

        //共一个sheet
        ExcelSheetData[] sds = new ExcelSheetData[1];
        //初始化第一个sheet
        ExcelSheetData sd = new ExcelSheetData("面试登记表");

        //设置家庭成员
        sd.addMapListData(familyList);
        //设置教育情况
        sd.addMapListData(teachList);
        //设置人员基本资料
        sd.addMapData(new Person(1,"无邪",1,"18630070626","554343346@qq.com","北京市昌平区西半壁店村",new Date()));
        //设置技能
        sd.addMapData("skill","凌波微步");

        sds[0] = sd;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();OutputStream os = response.getOutputStream()) {
            ExcelUtil.writeData("/面试登记表.xlsx", bos, sds);
            byte[] bytes = bos.toByteArray();

            response.reset();
            response.setHeader("Content-Disposition", "attachment; fileName=" + java.net.URLEncoder.encode("面试登记表.xlsx", "UTF-8"));
            response.setContentType("application/octet-stream; charset=utf-8");
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            log.error("导出任务所有信息到excel失败");
        }
    }
}
