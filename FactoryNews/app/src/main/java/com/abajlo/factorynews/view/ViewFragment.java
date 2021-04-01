package com.abajlo.factorynews.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abajlo.factorynews.R;
import com.abajlo.factorynews.model.Article;
import com.abajlo.factorynews.R;
import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    private TextView title;
    private TextView description;
    private ImageView image;
    Article article;

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_news,container, false);

        title = view.findViewById(R.id.articleTitle);
        title.setText(article.getTitle());

        description = view.findViewById(R.id.articleDescription);
        description.setText(article.getDescription());

        image = view.findViewById(R.id.articleImage);
        Glide.with(this)
                .asBitmap()
                .load(article.getUrlToImage())
                .into(image);





        return view;

    }

    public void setArticle(Article article){
        this.article = article;
    }


}
