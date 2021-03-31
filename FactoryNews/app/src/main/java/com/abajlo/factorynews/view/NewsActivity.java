package com.abajlo.factorynews.view;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abajlo.factorynews.R;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class NewsActivity extends AppCompatActivity {

    private static final String TAG = "NewsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Log.d(TAG, "onCreate: Started");
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "Checking for incoming intents...");

        if(getIntent().hasExtra("Image") && getIntent().hasExtra("Title") && getIntent().hasExtra("Description")){
            Log.d(TAG, "Found intended extras..");

            String ImageUrl = getIntent().getStringExtra("Image");
            String Title = getIntent().getStringExtra("Title");
            String Description = getIntent().getStringExtra("Description");

            setArticle(ImageUrl, Title, Description);
        }

    }

    private void setArticle(String imageUrl, String title, String description){

        TextView naslov = findViewById(R.id.articleTitle);
        naslov.setText(title);
        setTitle(title);

        ImageView image = findViewById(R.id.articleImage);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

        TextView opis = findViewById(R.id.articleDescription);
        opis.setText(description);



    }

}
