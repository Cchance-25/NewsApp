package com.example.chance.newsapp;

/**
 * Created by chance on 8/10/17.
 */

public class News {
    private String newsHeaderTitle;
    private String newsArticleSection;
    private String newsPublishDate;
    private String author;
    private String URL;

    public News() {
        // Empty default constructor
    }

    public News(String newsHeaderTitle, String newsArticleSection, String newsPublishDate, String author, String url) {
        this.newsHeaderTitle = newsHeaderTitle;
        this.newsArticleSection = newsArticleSection;
        this.newsPublishDate = newsPublishDate;
        this.author = author;
        URL = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNewsHeaderTitle() {
        return newsHeaderTitle;
    }

    public void setNewsHeaderTitle(String newsHeaderTitle) {
        this.newsHeaderTitle = newsHeaderTitle;
    }

    public String getNewsArticleSection() {
        return newsArticleSection;
    }

    public void setNewsArticleSection(String newsArticleSection) {
        this.newsArticleSection = newsArticleSection;
    }

    public String getNewsPublishDate() {
        return newsPublishDate;
    }

    public void setNewsPublishDate(String newsPublishDate) {
        this.newsPublishDate = newsPublishDate;
    }
}
