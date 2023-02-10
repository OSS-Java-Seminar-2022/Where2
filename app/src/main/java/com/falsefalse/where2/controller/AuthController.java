package com.falsefalse.where2.controller;

import com.falsefalse.where2.dto.RegistrationDto;
import com.falsefalse.where2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        var user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Validated @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        if (userService.existByUsername(user.getUsername()) || userService.existByEmail(user.getEmail())) {
            return "redirect:/register?fail";
        }
        userService.register(user);
        return "redirect:/home?success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
