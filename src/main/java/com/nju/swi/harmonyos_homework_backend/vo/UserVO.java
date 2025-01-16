package com.nju.swi.harmonyos_homework_backend.vo;

import com.nju.swi.harmonyos_homework_backend.po.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO {
    private int id;
    private String account;
    private String password;

    public User toPO(){
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        return user;
    }
}
