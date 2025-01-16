package com.nju.swi.harmonyos_homework_backend.serviceImpl;

import com.nju.swi.harmonyos_homework_backend.repository.UserRepository;
import com.nju.swi.harmonyos_homework_backend.service.UserService;
import com.nju.swi.harmonyos_homework_backend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVO register(UserVO user){
        // TODO
        return null;
    }
}
