package com.myboard.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "Hello My Board Web";
    }

    @GetMapping("/")
    public String root() {
        // "/question/list" URL 로 페이지를 리다이렉트 하라는 명령
        // localhost:8080 으로 접속해도 "/question/list" 로 URL 이 라다이렉트 됌
        return "redirect:/question/list";
    }
}
