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

public class NewsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "NewsActivity";
    List<Article> lista;
    private ViewPager viewPager;
    private ViewFragmentAdapter fragmentAdapter;
    private int position;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        Log.d(TAG, "onCreate: Started");
        getIncomingIntent();

        viewPager = findViewById(R.id.view_pager);
        fragmentAdapter = new ViewFragmentAdapter(getSupportFragmentManager(), lista);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(position);
        viewPager.setOnPageChangeListener(this);

    }

    private void getIncomingIntent() {
        Log.d(TAG, "Checking for incoming intents...");

        Intent i = getIntent();
        lista = (List<Article>) i.getSerializableExtra("LIST");
        position = i.getIntExtra("position", 0);
        setTitle(lista.get(position).getTitle());

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTitle(lista.get(position).getTitle());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}


