package com.nju.swi.harmonyos_homework_backend.serviceImpl;

import com.nju.swi.harmonyos_homework_backend.po.News;
import com.nju.swi.harmonyos_homework_backend.repository.NewsRepository;
import com.nju.swi.harmonyos_homework_backend.service.NewsService;
import com.nju.swi.harmonyos_homework_backend.service.WebSpiderService;
import com.nju.swi.harmonyos_homework_backend.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    WebSpiderService webSpiderService;

    @Override
    public List<NewsVO> getNews() {
        return newsRepository.findAll().stream().map(News::toVO).collect(Collectors.toList());
    }

    @Override
    public List<NewsVO> getNewsByType(String type) {

        List<News> newsList = newsRepository.findByType(type);
        Collections.reverse(newsList); // 反转原列表
        return newsList.stream()
                .map(News::toVO)
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean starNews(int id) {
        News news = newsRepository.findById(id).orElse(null);
        if(news == null){
            return false;
        }
        news.setStar(!news.getStar());
        newsRepository.save(news);
        return true;
    }

    @Override
    public List<NewsVO> getStarNews() {
        return newsRepository.findAll().stream()
                .filter(News::getStar)
                .map(News::toVO)
                .collect(Collectors.toList());
    }
}
