package com.example.mintnews.Models;

import java.util.ArrayList;

public class NewsModel {

    private int totalResults;
    private String status;
    private ArrayList<ArticlesModel> articles;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<ArticlesModel> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ArticlesModel> articles) {
        this.articles = articles;
    }

    public NewsModel(int totalResults, String status, ArrayList<ArticlesModel> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }
}
