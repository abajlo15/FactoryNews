package com.abajlo.factorynews.view;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.abajlo.factorynews.R;
import com.abajlo.factorynews.model.Article;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private static final String TAG = "NewsActivity";
    List<Article> lista;
    private ViewPager viewPager;
    private ViewFragmentAdapter fragmentAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_news);
        Log.d(TAG, "onCreate: Started");
        getIncomingIntent();

        viewPager = findViewById(R.id.view_pager);
        fragmentAdapter = new ViewFragmentAdapter(getSupportFragmentManager(), lista);
        viewPager.setAdapter(fragmentAdapter);
    }

    private void getIncomingIntent(){
        Log.d(TAG, "Checking for incoming intents...");

        Intent i = getIntent();
        lista = (List<Article>) i.getSerializableExtra("LIST");

        }

    }


