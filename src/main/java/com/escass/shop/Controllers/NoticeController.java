package com.escass.shop.Controllers;

import com.escass.shop.Entities.NoticeEntity;
import com.escass.shop.Repositories.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeRepository noticeRepository;

    @RequestMapping(value = "/notice", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView notice() {
        List<NoticeEntity> notices = noticeRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("notice");
        modelAndView.addObject("notices", notices);
        return modelAndView;
    }

}
