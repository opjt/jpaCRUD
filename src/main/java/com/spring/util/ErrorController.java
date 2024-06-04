package com.spring.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ErrorController {
    @GetMapping("/errorPage")
    public String handleError( String errorMessage, String redirectUrl, Model model) {

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("redirectUrl", redirectUrl);
        return "common/error"; // 에러 페이지의 뷰 이름
    }
}
