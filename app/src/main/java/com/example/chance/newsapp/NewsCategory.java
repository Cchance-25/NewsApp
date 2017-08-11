package com.example.chance.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsCategory extends AppCompatActivity {

    private final static int CULTURE_NUMBER = 0;
    private final static int BUSINESS_NUMBER = 1;
    private final static int LIFESTYLE_NUMBER = 2;
    private final static int FASHION_NUMBER = 3;
    private final static int ENVIRONMENT_NUMBER = 4;
    private final static int TECH_NUMBER = 5;
    private final static int TRAVEL_NUMBER = 6;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_category);

        ArrayList<String> cats = new ArrayList<>();
        cats.add(getString(R.string.cat_culture));
        cats.add(getString(R.string.cat_business));
        cats.add(getString(R.string.cat_lifestyle));
        cats.add(getString(R.string.cat_fashion));
        cats.add(getString(R.string.cat_environment));
        cats.add(getString(R.string.cat_technology));
        cats.add(getString(R.string.cat_travel));

        ListView rv = (ListView) findViewById(R.id.category_list_view);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.category_list_item, cats);
        rv.setAdapter(adapter);

        rv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case CULTURE_NUMBER:
                        i = new Intent(NewsCategory.this, Culture.class);
                        startActivity(i);
                        break;
                    case BUSINESS_NUMBER:
                        i = new Intent(NewsCategory.this, Business.class);
                        startActivity(i);
                        break;
                    case LIFESTYLE_NUMBER:
                        i = new Intent(NewsCategory.this, Lifestyle.class);
                        startActivity(i);
                        break;
                    case FASHION_NUMBER:
                        i = new Intent(NewsCategory.this, Fashion.class);
                        startActivity(i);
                        break;
                    case ENVIRONMENT_NUMBER:
                        i = new Intent(NewsCategory.this, EnvironmentActivity.class);
                        startActivity(i);
                        break;
                    case TECH_NUMBER:
                        i = new Intent(NewsCategory.this, Tech.class);
                        startActivity(i);
                        break;
                    case TRAVEL_NUMBER:
                        i = new Intent(NewsCategory.this, Travel.class);
                        startActivity(i);
                        break;
                }
            }
        });


    }

}
