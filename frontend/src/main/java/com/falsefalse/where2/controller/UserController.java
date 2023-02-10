package com.falsefalse.where2.controller;

import com.falsefalse.where2.models.UserModel;
import com.falsefalse.where2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    String getUsers (Model model) throws IOException, InterruptedException {
        model.addAttribute("users", userService.getAll());
        return "allUsers";
    }

    @GetMapping("{id}")
    String getUser (@PathVariable Integer id, Model model) throws IOException, InterruptedException {
        model.addAttribute("user", userService.get(id));
        return "userProfile";
    }

    @GetMapping("/new")
    String createVisitor (Model model) {
        model.addAttribute("newUser", new UserModel());
        return "createUser";
    }

    @PostMapping(value = "/new")
    String createVisitor (@ModelAttribute UserModel newUserModel, Model model) throws IOException, InterruptedException {
        model.addAttribute("user", userService.create(newUserModel));
        return "userProfile";
    }
}
