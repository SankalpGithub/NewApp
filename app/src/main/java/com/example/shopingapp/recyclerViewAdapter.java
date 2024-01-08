package com.example.shopingapp;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.MyViewHolder> {
    private ArrayList<Articles> articleArrayList;
    private Context context;

    public recyclerViewAdapter(ArrayList<Articles> articleArrayList, Context context) {
        this.articleArrayList = articleArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new recyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Articles article = articleArrayList.get(position);
        holder.title.setText(article.getTitle());
        holder.discription.setText(article.getDescription());
        if (article.getUrlToImage() == null) {
            Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/9/9b/Google_news_logo.png?20210712160907").placeholder(R.drawable.newslogo)
                    .into(holder.newsimageView);
        } else{
            Picasso.get().load(article.getUrlToImage())
                    .placeholder(R.drawable.newslogo).into(holder.newsimageView);
    }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(context, cards_details.class);
                i.putExtra("title", article.getTitle());
                i.putExtra("discription", article.getDescription());
                i.putExtra("url", article.getUrl());
                i.putExtra("getUrlToImage", article.getUrlToImage());
                i.putExtra("content", article.getContent());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title,discription;
       private ImageView newsimageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            discription = itemView.findViewById(R.id.discription);
            newsimageView = itemView.findViewById(R.id.newsimg);
        }
    }
}

