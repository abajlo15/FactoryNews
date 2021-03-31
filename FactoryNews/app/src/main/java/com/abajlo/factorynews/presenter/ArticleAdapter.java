package com.abajlo.factorynews.presenter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.R;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {

    private List<Article> articles = new ArrayList<>();


    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ArticleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {

        Article currentArticle = articles.get(position);
        holder.articleTitle.setText(currentArticle.getTitle());
        new DownloadImageTask(holder.articleImage).execute(currentArticle.getUrlToImage());


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<Article> articles){
        this.articles = articles;
        notifyDataSetChanged();
    }

    public List<Article> getArticles() {
        return articles;
    }

    class ArticleHolder extends RecyclerView.ViewHolder{
        private ImageView articleImage;
        private TextView articleTitle;


        public ArticleHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.imageArticleView);
            articleTitle = itemView.findViewById(R.id.textArticleView);
        }
    }
}
