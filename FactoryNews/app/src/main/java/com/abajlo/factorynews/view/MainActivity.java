package com.abajlo.factorynews.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.presenter.ArticleAdapter;
import com.abajlo.factorynews.presenter.ArticleVievModel;
import com.abajlo.factorynews.R;
import com.abajlo.factorynews.presenter.RecyclerItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ArticleVievModel articleVievModel;
    private long FIVE_MINUTES = 5*60*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ArticleAdapter adapter = new ArticleAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever

                       startActivity(new Intent(MainActivity.this, NewsActivity.class).putExtra("Image", adapter.getArticles().get(position).getUrlToImage()).putExtra("Title", adapter.getArticles().get(position).getTitle()). putExtra("Description" , adapter.getArticles().get(position).getDescription()));

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        articleVievModel = ViewModelProviders.of(this).get(ArticleVievModel.class);
        articleVievModel.getAllArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {

                adapter.setArticles(articles);


            }
        });


    }

}


