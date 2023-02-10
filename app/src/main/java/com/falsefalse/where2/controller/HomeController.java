package com.falsefalse.where2.controller;

import com.falsefalse.where2.service.FollowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("home")
public class HomeController {

    private final FollowingService followingService;

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("subs", followingService.getFollowing());
        return "index";
    }
}
