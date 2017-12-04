package com.example.android.newsapp;

/**
 * Created by azozs on 10/27/2017.
 */

public class News {
    private String nTitle;
    private String nSection;
    private String nAuthorName;
    private String nUrl;
    private String nThumbnail;
    private String nPublishDate;


    public News(String title, String section, String url, String authorName, String thumbnail, String publishDate) {
        nTitle = title;
        nSection = section;
        nAuthorName = authorName;
        nThumbnail = thumbnail;
        nUrl = url;
        nPublishDate = publishDate;
    }

    public String getnThumbnail() {
        return nThumbnail;
    }

    public String getnPublishDate() {
        return nPublishDate;
    }

    public String getnTitle() {
        return nTitle;
    }

    public String getnSection() {
        return "Category: " + nSection;
    }

    public String getnAuthorName() {
        return nAuthorName;
    }

    public String getnUrl() {
        return nUrl;
    }
}
