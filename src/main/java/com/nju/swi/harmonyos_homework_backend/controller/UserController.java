package com.nju.swi.harmonyos_homework_backend.controller;
import com.nju.swi.harmonyos_homework_backend.service.UserService;
import com.nju.swi.harmonyos_homework_backend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserVO register(@RequestBody UserVO userVO) {
        return userService.register(userVO);
    }
}
