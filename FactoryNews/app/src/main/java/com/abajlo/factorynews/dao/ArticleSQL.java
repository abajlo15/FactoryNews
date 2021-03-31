package com.abajlo.factorynews.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.model.Data;

import java.util.List;

@Dao
public interface ArticleSQL {

    @Insert
    public void addArticle(Article article);

    @Delete
    public void deleteArticle(Article article);

    @Update
    public void updateArticle(Article article);

    @Query("SELECT * FROM 'bazaArticle'")
    LiveData<List<Article>> getAllArticles();

}
