package com.nju.swi.harmonyos_homework_backend.po;

import com.nju.swi.harmonyos_homework_backend.vo.NewsVO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "news", indexes = {
        @Index(name = "idx_news_id", columnList = "id"),
})
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "author")
    private String author;

    @Basic
    @Column(name = "date")
    private String date;

    @Basic(optional = false)
    @Column(name = "type")
    private String type;

    @Basic(optional = false)
    @Column(name = "url")
    private String url;

    public News(String title, String content, String author, String date) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public NewsVO toVO() {
        NewsVO newsVO = new NewsVO();
        newsVO.setId(id);
        newsVO.setTitle(title);
        newsVO.setContent(content);
        newsVO.setAuthor(author);
        newsVO.setDate(date);
        newsVO.setType(type);
        newsVO.setUrl(url);
        return newsVO;
    }
}
