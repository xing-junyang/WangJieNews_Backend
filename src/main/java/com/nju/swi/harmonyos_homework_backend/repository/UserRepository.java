package com.nju.swi.harmonyos_homework_backend.repository;

import com.nju.swi.harmonyos_homework_backend.po.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount(String account);
}