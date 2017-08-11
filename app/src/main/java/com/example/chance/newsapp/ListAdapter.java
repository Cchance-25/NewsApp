package com.example.chance.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chance on 8/10/17.
 */

public class ListAdapter extends ArrayAdapter<News> {

    private ViewHolder viewHolder;
    private String DATE_TIME_SEPERATURE = "T";

    public ListAdapter(@NonNull Context context, @NonNull ArrayList<News> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        News current = getItem(position);

        viewHolder.articleName.setText(current.getNewsHeaderTitle());
        viewHolder.articleSection.setText(current.getNewsArticleSection());
        viewHolder.articlePublishDate.setVisibility(View.GONE);
        viewHolder.author.setVisibility(View.GONE);
        if(current.getNewsPublishDate() != null) {
            String[] dateAndTime = current.getNewsPublishDate().split(DATE_TIME_SEPERATURE);
            viewHolder.articlePublishDate.setText(dateAndTime[0]);
            viewHolder.articlePublishDate.setVisibility(View.VISIBLE);
        }
        if(current.getAuthor() != null) {
            viewHolder.author.setText(current.getAuthor());
            viewHolder.author.setVisibility(View.VISIBLE);
        }

        return view;
    }

    static class ViewHolder {
        private TextView articleName, articleSection, articlePublishDate, author;

        public ViewHolder(@NonNull View view) {
            this.articleName = (TextView) view.findViewById(R.id.article_title);
            this.articleSection = (TextView) view.findViewById(R.id.section_name);
            this.articlePublishDate = (TextView) view.findViewById(R.id.publish_date);
            this.author = (TextView) view.findViewById(R.id.author);
        }
    }
}


