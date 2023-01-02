package com.falsefalse.where2.api;

import com.falsefalse.where2.models.Visitor;
import com.falsefalse.where2.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("visitors")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public List<Visitor> getVisitors() {
        return visitorService.getVisitors();
    }

    @GetMapping("{id}")
    public Visitor getVisitor(@PathVariable Integer id) {
        return visitorService.getVisitor(id);
    }

    @PostMapping
    public Visitor createVisitor(@RequestBody Visitor newVisitor) {
        return visitorService.createVisitor(newVisitor);
    }

    @PutMapping("{id}")
    public Visitor putVisitor(@PathVariable Integer id, @RequestBody Visitor newVisitor) {
        return visitorService.putVisitor(id, newVisitor);
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteVisitor(@PathVariable Integer id) {
        return visitorService.deleteVisitor(id);
    }
}
