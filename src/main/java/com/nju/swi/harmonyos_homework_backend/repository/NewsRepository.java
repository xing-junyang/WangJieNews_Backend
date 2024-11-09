package com.nju.swi.harmonyos_homework_backend.repository;

import com.nju.swi.harmonyos_homework_backend.po.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findByType(String type);
}
