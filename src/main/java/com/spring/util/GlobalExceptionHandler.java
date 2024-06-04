package com.spring.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException ex, Model model) {
        log.error("CustomException occurred: ", ex);
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("redirectUrl", ex.getRedirectUrl());
        return "common/error"; // 에러 페이지로 이동
    }

//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception ex, Model model) {
//        model.addAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
//        return "common/error";
//    }

}
