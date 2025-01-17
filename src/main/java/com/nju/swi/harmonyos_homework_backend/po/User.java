package com.nju.swi.harmonyos_homework_backend.po;

import com.nju.swi.harmonyos_homework_backend.vo.UserVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user", indexes = {
        @Index(name = "idx_user_id", columnList = "id"),
})
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

    @Basic
    @Column(name = "star_news")
    private List<Integer> starNews;

    public UserVO toVO(){
        UserVO vo = new UserVO();
        vo.setAccount(account);
        vo.setPassword(password);
        vo.setStarNews(starNews);
        return vo;
    }
}
