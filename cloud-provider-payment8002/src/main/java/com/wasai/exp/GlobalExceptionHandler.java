package com.wasai.exp;

import com.wasai.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 骑猪去青岛
 * @data 2024-06-30
 * @time 15:47
 * @desc
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        System.out.println("come in GlobalExceptionHandler");
        log.error("exception:{}", e.getMessage());
        return ResultData.fail("500", e.getMessage());
    }
}
