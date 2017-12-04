package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by azozs on 10/27/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    private Context aContext;

    public NewsAdapter(@NonNull Context context, @NonNull List<News> objects) {
        super(context, 0, objects);
        aContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News news = getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view, parent, false);
            viewHolder.title = convertView.findViewById(R.id.text_title);
            viewHolder.title.setText(news.getnTitle());
            viewHolder.subTitle = convertView.findViewById(R.id.text_sub_title);
            viewHolder.subTitle.setText(news.getnSection());
            viewHolder.imageView = convertView.findViewById(R.id.image_view);
            viewHolder.author = convertView.findViewById(R.id.text_author);
            viewHolder.author.setText(news.getnAuthorName());
            viewHolder.publishDate = convertView.findViewById(R.id.text_publish_date);
            viewHolder.publishDate.setText(news.getnPublishDate());
        }
        try {
            Glide.with(getContext())
                    .load(news.getnThumbnail())
                    .into(viewHolder.imageView);
        } catch (Exception e) {
            Glide.with(getContext())
                    .load(aContext.getString(R.string.guardian))
                    .into(viewHolder.imageView);
        }
        convertView.setTag(viewHolder);
        return convertView;
    }
}
