package com.abajlo.factorynews.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.presenter.ArticleAdapter;
import com.abajlo.factorynews.presenter.ArticleVievModel;
import com.abajlo.factorynews.R;
import com.abajlo.factorynews.presenter.RecyclerItemClickListener;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ArticleVievModel articleVievModel;
    final ArticleAdapter adapter = new ArticleAdapter();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoadingData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showData();

            }
        }, 1500);




    }

    private boolean getLoadingData(){
        final LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        },1500);


        return false;
    }


    private void showData(){
        //populateList();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever

                        List<Article> lista = adapter.getArticles();
                        startActivity(new Intent(MainActivity.this, NewsActivity.class).putExtra("LIST", (Serializable) lista));

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        recyclerView.setAdapter(adapter);
        articleVievModel = ViewModelProviders.of(this).get(ArticleVievModel.class);
        articleVievModel.getAllArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {

                adapter.setArticles(articles);


            }
        });

    }



}


