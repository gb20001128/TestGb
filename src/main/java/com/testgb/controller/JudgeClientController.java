package com.testgb.controller;

import com.testgb.utils.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;


/**
 * 判断是app请求还是web请求
 * @author gb
 */
@RestController
public class JudgeClientController {

    @GetMapping("/checkAgent")
    public String testUa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ua = request.getHeader("User-Agent");
        if(StringUtil.checkAgentIsMobile(ua)){
            System.out.println("app");
            return "app";
        }else{
            System.out.println("web");
            return "web";
        }

    }
}
