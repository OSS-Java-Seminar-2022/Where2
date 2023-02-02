package com.falsefalse.where2.controller;

import com.falsefalse.where2.models.UserModel;
import com.falsefalse.where2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/visitors")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    String getVisitors (Model model) throws IOException, InterruptedException {
        model.addAttribute("visitors", userService.getAll());
        return "allVisitors";
    }

    @GetMapping("{id}")
    String getVisitors (@PathVariable Integer id, Model model) throws IOException, InterruptedException {
        model.addAttribute("visitor", userService.get(id));
        return "visitor";
    }

    @GetMapping("/new")
    String createVisitor (Model model) {
        model.addAttribute("newVisitor", new UserModel());
        return "createVisitor";
    }

    @PostMapping(value = "/new")
    String createVisitor (@ModelAttribute UserModel newUserModel, Model model) throws IOException, InterruptedException {
        model.addAttribute("visitor", userService.create(newUserModel));
        return "visitor";
    }
}
