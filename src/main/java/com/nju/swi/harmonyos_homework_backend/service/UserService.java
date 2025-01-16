package com.nju.swi.harmonyos_homework_backend.service;

import com.nju.swi.harmonyos_homework_backend.vo.UserVO;

public interface UserService {
    public Boolean register(UserVO user);

    public Boolean login(UserVO user);
}