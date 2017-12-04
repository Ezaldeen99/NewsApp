package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by azozs on 10/29/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String lUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        lUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (lUrl == null) {
            return null;
        }
        return JsonData.fetchData(lUrl);
    }
}
