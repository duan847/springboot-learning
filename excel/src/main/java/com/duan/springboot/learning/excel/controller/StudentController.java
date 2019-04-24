package com.duan.springboot.learning.excel.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.duan.springboot.learning.excel.pojo.vo.StudentScoreVO;
import com.duan.springboot.learning.excel.util.excel.ExcelUtils;
import com.duan.springboot.learning.excel.util.excel.SheetData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * 学生controller接口
 * 1. 导出学生列表excel
 * 2. 导入学生类标excel
 * 3. 导出学生制式+列表excel
 *
 * @author duanjw
 */
@RestController
@RequestMapping("student")
@Slf4j
public class StudentController {

    @GetMapping("export")
    public void exportByIds(Map map, HttpServletResponse response) {
        List<StudentScoreVO> studentScoreVOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            studentScoreVOList.add(new StudentScoreVO(i));
        }
        String ids = ObjectUtil.toString(map.get("ids"));
//        assert StrUtil.isEmpty(ids);
        SheetData[] sds = new SheetData[2];
        SheetData sd = new SheetData("任务所有信息");
        sd.addDatas(studentScoreVOList);
        sd.put("name","张");
        sds[0] = sd;
        sd = new SheetData("任务所有信息2");
        studentScoreVOList.remove(0);
        sd.addDatas(studentScoreVOList);
        sds[1] = sd;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();OutputStream os = response.getOutputStream()) {
            ExcelUtils.writeData("/学生.xlsx", bos, sds);
            byte[] bytes = bos.toByteArray();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                String format = simpleDateFormat.format(DateUtil.date());
                response.reset();
                response.setHeader("Content-Disposition", "attachment; fileName=" + java.net.URLEncoder.encode("导出任务" + format + ".xlsx", "UTF-8"));
                response.setContentType("application/octet-stream; charset=utf-8");
                os.write(bytes);
                os.flush();
        } catch (IOException e) {
            log.error("导出任务所有信息到excel失败");
        }
    }
}
