package com.example.chance.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by chance on 8/10/17.
 */

public class BackgroundLoader extends AsyncTaskLoader<ArrayList<News>> {

    private static final String TAG = BackgroundLoader.class.getSimpleName();
    private String url;

    public BackgroundLoader(Context context, String url){
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        if (url == null)
            return null;
        ArrayList<News> list = Utils.getFinalList(url);
        return list;
    }


}
