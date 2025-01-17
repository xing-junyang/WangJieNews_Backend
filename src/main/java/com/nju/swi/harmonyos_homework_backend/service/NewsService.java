package com.nju.swi.harmonyos_homework_backend.service;

import com.nju.swi.harmonyos_homework_backend.vo.NewsVO;

import java.util.List;

public interface NewsService {
    public List<NewsVO> getNews();

    public List<NewsVO> getNewsByType(String type);

    public Boolean starNews(int userid, int id);

    public List<NewsVO> getStarNews(int userid);
}
