package com.abajlo.factorynews.presenter;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.dao.ArticleRepository;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ArticleVievModel extends AndroidViewModel {

    private ArticleRepository repository;
    private LiveData<List<Article>> allArticles;

    public ArticleVievModel(@NonNull Application application) {
        super(application);
        repository = new ArticleRepository(application);
        allArticles = repository.getAllArticles();

    }

    public void insert(Article article){
        repository.insert(article);
    }

    public void delete(Article article){
        repository.delete(article);
    }

    public LiveData<List<Article>> getAllArticles(){
        return allArticles;
    }
}
