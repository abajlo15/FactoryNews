package com.abajlo.factorynews.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.abajlo.factorynews.model.Article;

import java.util.List;

public class ViewFragmentAdapter extends FragmentStatePagerAdapter {

    List<Article> lista;

    public ViewFragmentAdapter(@NonNull FragmentManager fm, List<Article> list) {
        super(fm);
        lista = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        ViewFragment viewFragment = new ViewFragment();
        viewFragment.setArticle(lista.get(position));

        return viewFragment;


    }

    @Override
    public int getCount() {
        return lista.size();
    }
}
