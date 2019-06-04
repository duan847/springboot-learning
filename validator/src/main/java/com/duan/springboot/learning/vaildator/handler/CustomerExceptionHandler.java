package com.duan.springboot.learning.vaildator.handler;

import com.duan.springboot.learning.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handle(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
//        //获取参数校验错误集合
//        List<FieldError> fieldErrors = exception.getFieldErrors();
//        //格式化以提供友好的错误提示
//        String data = String.format("参数校验错误（%s）：%s", fieldErrors.size(),
//                fieldErrors.stream()
//                        .map(FieldError::getDefaultMessage)
//                        .collect(Collectors.joining(";")));
//        //参数校验失败响应失败个数及原因
        return R.failed(bindingResult.getFieldError().getDefaultMessage());
    }
}