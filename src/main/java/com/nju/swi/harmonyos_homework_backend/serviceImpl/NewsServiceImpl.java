package com.nju.swi.harmonyos_homework_backend.serviceImpl;

import com.nju.swi.harmonyos_homework_backend.po.News;
import com.nju.swi.harmonyos_homework_backend.po.User;
import com.nju.swi.harmonyos_homework_backend.repository.NewsRepository;
import com.nju.swi.harmonyos_homework_backend.repository.UserRepository;
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
    UserRepository userRepository;

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
    public Boolean starNews(int userid, int id) {
        News news = newsRepository.findById(id).orElse(null);
        User user = userRepository.findById(userid).orElse(null);
        if(news == null || user == null){
            return false;
        }
        List<Integer> starNews = user.getStarNews();
        if(starNews.contains(id)) {
            starNews.remove(Integer.valueOf(id));
        } else {
            starNews.add(id);
        }
        user.setStarNews(starNews);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<NewsVO> getStarNews(int userid) {
        User user = userRepository.findById(userid).orElse(null);
        if(user == null){
            return null;
        }
        List<Integer> starNews = user.getStarNews();
        return starNews.stream()
                .map(newsRepository::findById)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .map(News::toVO)
                .collect(Collectors.toList());
    }
}
