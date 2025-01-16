package com.nju.swi.harmonyos_homework_backend.serviceImpl;

import com.nju.swi.harmonyos_homework_backend.po.News;
import com.nju.swi.harmonyos_homework_backend.repository.NewsRepository;
import com.nju.swi.harmonyos_homework_backend.service.WebSpiderService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class WebSpiderServiceImpl implements WebSpiderService {
    @Autowired
    NewsRepository newsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void feedNewsRepository() {

        List<News> newsList = new ArrayList<>();
        newsList.addAll(fetchNewsFromWeb("https://tech.163.com", "Technology"));
        newsList.addAll(fetchNewsFromWeb("https://money.163.com", "finance"));
        newsList.addAll(fetchNewsFromWeb("https://news.163.com/domestic/", "Feature"));
        newsList.addAll(fetchNewsFromWeb("https://sports.163.com", "Sports"));
        newsList.addAll(fetchNewsFromWeb("https://tech.163.com/game/", "Games"));
        newsList.addAll(fetchNewsFromWeb("https://culture.163.com", "Health"));
        newsList.addAll(fetchNewsFromWeb("https://news.163.com/world/", "World"));
        newsRepository.deleteAll();
        newsRepository.saveAll(newsList);
    }

    private List<News> fetchNewsFromWeb(String url, String type) {
        List<News> newsList = new ArrayList<>();
        String driverPath = System.getProperty("user.dir") + "/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // 开启无头模式
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".news_article"))
            );
            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);
            Elements newsElements = doc.select(".news_article");

            for (Element newsElement : newsElements) {
                News news = new News();
                String title = newsElement.select(".news_title").text();
                news.setTitle(title);
                String newsUrl = newsElement.select(".news_title").first().child(0).child(0).attr("href");
                news.setUrl(newsUrl);
                String imageUrl = "";
                if(newsElement.select(".na_pic" ).first() != null) {
                    newsElement.select(".na_pic" ).first().child(0);
                    if(Objects.equals(type, "finance" )){
                        imageUrl = newsElement.select(".na_pic" ).first().child(0).attr("data-original" );
                    }else{
                        imageUrl = newsElement.select(".na_pic" ).first().child(0).attr("src" );
                    }
                }

                news.setContent(imageUrl);
                String author = newsElement.select(".keywords").text(); // 假设作者信息
                String date = newsElement.select(".time").text(); // 假设发布日期，实际可以从页面获取
                news.setAuthor(author);
                news.setDate(date);
                news.setType(type);
                if(newsUrl.isEmpty()){
                    continue;
                }
                newsList.add(news);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
