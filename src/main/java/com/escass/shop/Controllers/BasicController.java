package com.escass.shop.Controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Controller
@RequestMapping(value = "/")
public class BasicController {

    @GetMapping("/")
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/about")
    @ResponseBody
    String about() {
        return "피싱사이트에요";
    }

//    @RequestMapping(value = "/date", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
//    @ResponseBody
//    public String date() {
//        System.out.println(LocalDateTime.now());
//        // ZonedDateTime LocalDateTime과 비슷
//        return ZonedDateTime.now().toString();
//    }
}
