package com.nju.swi.harmonyos_homework_backend.vo;

import com.nju.swi.harmonyos_homework_backend.po.News;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsVO {
    private int Id;
    private String Title;
    private String Content;
    private String Date;
    private String Author;
    private String Type;
    private String Url;
    private Boolean star;

    public News toPO(){
        News news = new News();
        news.setId(Id);
        news.setTitle(Title);
        news.setContent(Content);
        news.setDate(Date);
        news.setAuthor(Author);
        news.setType(Type);
        news.setUrl(Url);
        news.setStar(star);
        return news;
    }
}
