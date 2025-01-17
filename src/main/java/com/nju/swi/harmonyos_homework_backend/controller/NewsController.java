package com.nju.swi.harmonyos_homework_backend.controller;

import com.nju.swi.harmonyos_homework_backend.po.News;
import com.nju.swi.harmonyos_homework_backend.service.NewsService;
import com.nju.swi.harmonyos_homework_backend.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/test")
    public NewsVO test(){
        return new News("Test Title", "Test Content", "XJY", "2024/6/1").toVO();
    }

    @GetMapping("/get_news")
    public List<NewsVO> getNews(){
        return newsService.getNews();
    }

    @GetMapping("/get_news_by_type")
    public List<NewsVO> getNewsByType(@RequestParam String type){
        return newsService.getNewsByType(type);
    }

    @GetMapping("/star_news")
    public Boolean starNews(@RequestParam int id){
        return newsService.starNews(id);
    }

    @GetMapping("/get_star_news")
    public List<NewsVO> getStarNews(){
        return newsService.getStarNews();
    }
}
