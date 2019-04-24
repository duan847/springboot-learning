package com.duan.springboot.learning.excel;

import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExcelApplicationTest.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ExcelApplicationTest {

    @Test
    public void readExcel(){

    }
}
