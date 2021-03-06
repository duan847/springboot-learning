package com.duan.springboot.learning.excel.controller;

import com.duan.springboot.learning.excel.pojo.vo.*;
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
import java.math.BigDecimal;
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
        //培训情况数据
        List<Train> trainList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            trainList.add(new Train(i,"PMP管理" + i + "级", "PMP", new Date()));
        }
        //工作经历
        List<WorkExperience> workExperienceList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            workExperienceList.add(new WorkExperience(i,"北京第" + i + "墓场","守墓", new Date(), new Date(),new BigDecimal(1000), "张" + i, "1863007062" + i, "寂寞+" + i));
        }

        //共一个sheet（就一个sheet可以不写，多个需要用数组）
//        ExcelSheetData[] sds = new ExcelSheetData[1];
        //初始化第一个sheet（sheet重命名）
        ExcelSheetData sd = new ExcelSheetData("面试登记表");
        //设置家庭成员（没有重命名，在模板中使用：${Family.id}
        sd.addMapListData(familyList);
        //设置教育情况（重命名，在模板中使用：${teachList.id}）
        sd.addMapListData("teachList",teachList);
        //设置培训情况、工作经历（没有重命名，一次添加多个）
        sd.addMapListData(trainList, workExperienceList);
        //设置人员基本资料（没有重命名，在模板中使用：#{Person.id}）
        sd.addMapData(new Person(1,"无邪",1,"18630070626","554343346@qq.com","北京市昌平区西半壁店村",new Date(),"浑身优点","太完美","四级","日语"));
        //设置技能（模板中使用：#{skill}）)
        sd.addMapData("skill","凌波微步");

//        sds[0] = sd;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();OutputStream os = response.getOutputStream()) {
            ExcelUtil.writeData("/面试登记表.xlsx", bos, sd);
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
