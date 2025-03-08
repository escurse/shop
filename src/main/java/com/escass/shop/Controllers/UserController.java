package com.escass.shop.Controllers;

import com.escass.shop.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/register")
    public String register(Authentication auth) {
        if (auth.isAuthenticated()) {
            return "redirect:/list";
        }
        return "/register";
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String displayName) throws Exception {
        userService.addUser(username, password, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
//        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("고급유저")));
        if (auth == null) {
            return "redirect:/login";
        }
        if (!auth.isAuthenticated()) {
            return "redirect:/login";
        }
        return "/myPage";
    }
}
