package com.example.chance.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseFetchClassActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {


    private final static String TAG = BaseFetchClassActivity.class.getSimpleName();
    private ArrayList<News> list;
    private ListView listView;
    private ListAdapter adapter;
    private View loadingIndicator;
    private TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list_view);
        loadingIndicator = findViewById(R.id.progress_bar);
        loadingText = (TextView) findViewById(R.id.loading_message);

        listView = (ListView) findViewById(R.id.general_list_view);
        list = new ArrayList<>();
        adapter = new ListAdapter(this, list);
        listView.setAdapter(adapter);

        fetchData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currentArticleURL = list.get(position).getURL();
                Intent openWebBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(currentArticleURL));
                startActivity(openWebBrowser);
            }
        });
    }

    private boolean fetchData() {
        if (isConnected()) {
            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, this);
            loadingIndicator.setVisibility(View.VISIBLE);
            loadingText.setVisibility(View.VISIBLE);
            return true;

        } else {
            loadingIndicator.setVisibility(View.GONE);
            loadingText.setText(R.string.no_connection);
            return false;
        }
    }

    //Check Whether Internet Connection is Available or Not
    private boolean isConnected() {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            isConnected = true;
        return isConnected;
    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        Utils.clearList();
        Intent i = getIntent();
        String REQUEST_URL = i.getDataString();
        return new BackgroundLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> data) {
        loadingIndicator.setVisibility(View.GONE);
        loadingText.setVisibility(View.GONE);
        adapter.addAll(data);

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        adapter.clear();
    }


}
