package com.example.chance.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsMainActivity extends AppCompatActivity {

    private final static String API_KEY = "d6f0833e-7009-4d64-86d1-97d10c3661fd";
    private final static int CULTURE_NUMBER = 0;
    private final static int BUSINESS_NUMBER = 1;
    private final static int LIFESTYLE_NUMBER = 2;
    private final static int FASHION_NUMBER = 3;
    private final static int ENVIRONMENT_NUMBER = 4;
    private final static int TECH_NUMBER = 5;
    private final static int TRAVEL_NUMBER = 6;
    private static String BASE_REQUEST_URL = "https://content.guardianapis.com/";
    private Intent i;
    private Uri url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_list_view);

        ArrayList<String> cats = new ArrayList<>();
        cats.add(getString(R.string.cat_culture));
        cats.add(getString(R.string.cat_business));
        cats.add(getString(R.string.cat_lifestyle));
        cats.add(getString(R.string.cat_fashion));
        cats.add(getString(R.string.cat_environment));
        cats.add(getString(R.string.cat_technology));
        cats.add(getString(R.string.cat_travel));

        ListView rv = (ListView) findViewById(R.id.general_list_view);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.category_list_item, cats);
        rv.setAdapter(adapter);

        View loadingIndicator = findViewById(R.id.progress_bar),
                loadingText = findViewById(R.id.loading_message);
        loadingIndicator.setVisibility(View.GONE);
        loadingText.setVisibility(View.GONE);

        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case CULTURE_NUMBER:
                        i = new Intent(NewsMainActivity.this, BaseFetchClassActivity.class);
                        url = makeUri("culture");
                        i.setData(url);
                        startActivity(i);
                        break;
                    case BUSINESS_NUMBER:
                        i = new Intent(NewsMainActivity.this, BaseFetchClassActivity.class);
                        url = makeUri("business");
                        i.setData(url);
                        startActivity(i);
                        break;
                    case LIFESTYLE_NUMBER:
                        i = new Intent(NewsMainActivity.this, BaseFetchClassActivity.class);
                        url = makeUri("lifeandstyle");
                        i.setData(url);
                        startActivity(i);
                        break;
                    case FASHION_NUMBER:
                        i = new Intent(NewsMainActivity.this, BaseFetchClassActivity.class);
                        url = makeUri("fashion");
                        i.setData(url);
                        startActivity(i);
                        break;
                    case ENVIRONMENT_NUMBER:
                        i = new Intent(NewsMainActivity.this, BaseFetchClassActivity.class);
                        url = makeUri("environment");
                        i.setData(url);
                        startActivity(i);
                        break;
                    case TECH_NUMBER:
                        i = new Intent(NewsMainActivity.this, BaseFetchClassActivity.class);
                        url = makeUri("technology");
                        i.setData(url);
                        startActivity(i);
                        break;
                    case TRAVEL_NUMBER:
                        i = new Intent(NewsMainActivity.this, BaseFetchClassActivity.class);
                        url = makeUri("travel");
                        i.setData(url);
                        startActivity(i);
                        break;
                }
            }
        });

    }

    private Uri makeUri(String category) {
        Uri url = Uri.withAppendedPath(Uri.parse(BASE_REQUEST_URL), category + "?api-key=" + API_KEY);
        return url;
    }


}
