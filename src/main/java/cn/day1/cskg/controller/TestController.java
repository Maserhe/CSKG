package cn.day1.cskg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * 测试Controller
 *
 * @author maserhe
 * @date 2023/1/4 20:13
 **/
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/123")
    public String test() {
        return "123";
    }
}
