package com.example.pethelp;

public class Article {
    private String title;
    private String description;
    private String link;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public Article(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }


}
