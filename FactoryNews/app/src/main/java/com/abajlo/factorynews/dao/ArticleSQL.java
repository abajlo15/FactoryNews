package com.abajlo.factorynews.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.model.Data;

import java.util.List;

@Dao
public interface ArticleSQL {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addArticle(Article article);

    @Delete
    public void deleteArticle(Article article);

    @Update
    public void updateArticle(Article article);

    @Query("SELECT * FROM 'bazaArticle'")
    LiveData<List<Article>> getAllArticles();

    @Query("SELECT * FROM 'bazaArticle'")
    List<Article> localArticles();

    @Query("DELETE FROM 'bazaArticle'")
    void clearAll();

}
