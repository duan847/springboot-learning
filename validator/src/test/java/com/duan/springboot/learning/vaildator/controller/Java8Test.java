package com.duan.springboot.learning.vaildator.controller;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Optional;

public class Java8Test {

    @Test
    public void test01() throws FileNotFoundException {
        Object s = Optional.ofNullable("1").orElseThrow(FileNotFoundException::new);
        System.out.println(s);
    }

}
