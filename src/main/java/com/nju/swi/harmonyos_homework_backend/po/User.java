package com.nju.swi.harmonyos_homework_backend.po;

import com.nju.swi.harmonyos_homework_backend.vo.UserVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "account")
    private String account;

    @Basic
    @Column(name = "password")
    private String password;

    public UserVO toVO(){
        UserVO vo = new UserVO();
        vo.setAccount(account);
        vo.setPassword(password);
        return vo;
    }
}
