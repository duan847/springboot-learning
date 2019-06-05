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

/**
 * 自定义异常处理
 * @author duanjw
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 参数校验失败异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handle(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return R.failed(bindingResult.getFieldError().getField() + bindingResult.getFieldError().getDefaultMessage());
    }
}
