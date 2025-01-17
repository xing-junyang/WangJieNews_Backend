package com.nju.swi.harmonyos_homework_backend.vo;

import com.nju.swi.harmonyos_homework_backend.po.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserVO {
    private String account;
    private String password;
    private List<Integer> starNews;

    public User toPO(){
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStarNews(starNews);
        return user;
    }
}
