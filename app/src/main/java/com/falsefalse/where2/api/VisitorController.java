package com.falsefalse.where2.api;

import com.falsefalse.where2.models.Visitor;
import com.falsefalse.where2.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("visitors")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public Visitor getVisitor() {
        return visitorService.getVisitor();
    }
}
