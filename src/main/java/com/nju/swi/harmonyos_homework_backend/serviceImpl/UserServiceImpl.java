package com.nju.swi.harmonyos_homework_backend.serviceImpl;

import com.nju.swi.harmonyos_homework_backend.po.User;
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
    public Boolean register(UserVO user){
        User oldUser = userRepository.findByAccount(user.getAccount());
        if(oldUser != null){
            return false;
        }
        userRepository.save(user.toPO());
        return true;
    }

    @Override
    public Boolean login(UserVO user) {
        User oldUser = userRepository.findByAccount(user.getAccount());
        return oldUser != null && oldUser.getPassword().equals(user.getPassword());
    }
}
