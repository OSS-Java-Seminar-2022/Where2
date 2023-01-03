package com.falsefalse.where2.rest;

import com.falsefalse.where2.models.Visitor;
import com.falsefalse.where2.service.VisitorService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    VisitorService visitorService;

    @GetMapping()
    String getVisitors (Model model) throws IOException, InterruptedException {
        model.addAttribute("visitors", visitorService.getAll());
        return "allVisitors";
    }

    @GetMapping("{id}")
    String getVisitors (@PathVariable Integer id, Model model) throws IOException, InterruptedException {
        model.addAttribute("visitor", visitorService.get(id));
        return "visitor";
    }

    @GetMapping("/new")
    String createVisitor (Model model) {
        model.addAttribute("newVisitor", new Visitor());
        return "createVisitor";
    }

    @PostMapping(value = "/new")
    String createVisitor (@ModelAttribute Visitor newVisitor, Model model) throws IOException, InterruptedException {
        model.addAttribute("visitor", visitorService.create(newVisitor));
        return "visitor";
    }
}
