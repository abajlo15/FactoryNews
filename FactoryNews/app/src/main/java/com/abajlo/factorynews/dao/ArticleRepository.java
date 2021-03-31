package com.abajlo.factorynews.dao;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.abajlo.factorynews.model.Article;

import java.util.List;

public class ArticleRepository {

    private ArticleSQL articleSQL;
    private LiveData<List<Article>> allArticles;

    public ArticleRepository(Application application){

        articleDatabase database = articleDatabase.getDatabase(application);
        articleSQL = database.articleSQL();
        allArticles = articleSQL.getAllArticles();
    }

    public void insert(Article article){
        new InsertArticleAsyncTask(articleSQL).execute(article);
    }

    public void delete(Article article){
        new DeleteArticleAsyncTask(articleSQL).execute(article);
    }

    public LiveData<List<Article>> getAllArticles(){
        return allArticles;
    }

    private static class InsertArticleAsyncTask extends AsyncTask<Article, Void, Void> {

        private ArticleSQL articleSQL;

        private InsertArticleAsyncTask(ArticleSQL articleSQL){
            this.articleSQL = articleSQL;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            articleSQL.addArticle(articles[0]);
            return null;
        }
    }

    private static class DeleteArticleAsyncTask extends AsyncTask<Article, Void, Void> {

        private ArticleSQL articleSQL;

        private DeleteArticleAsyncTask(ArticleSQL articleSQL){
            this.articleSQL = articleSQL;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            articleSQL.deleteArticle(articles[0]);
            return null;
        }
    }
}

