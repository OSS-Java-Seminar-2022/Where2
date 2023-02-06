package com.falsefalse.where2.api;

import com.falsefalse.where2.models.UserModel;
import com.falsefalse.where2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserModel> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public UserModel get(@PathVariable Integer id) {
        return userService.get(id);
    }

    //todo: remove redundant
    @PostMapping
    public UserModel create(@RequestBody UserModel newUserModel) {
        return userService.create(newUserModel);
    }

    @PutMapping("{id}")
    public UserModel update(@PathVariable Integer id, @RequestBody UserModel newUserModel) {
        return userService.update(id, newUserModel);
    }

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable Integer id) {
        return userService.delete(id);
    }
}
