package com.example.shopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class cards_details extends AppCompatActivity {
TextView title,discription,content;
ImageView imgnews;
Button visitnews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_details);
        title = findViewById(R.id.title);
        discription = findViewById(R.id.discription);
        content = findViewById(R.id.content);
        imgnews = findViewById(R.id.dp);
        visitnews = findViewById(R.id.visitnews);
        Bundle extras = getIntent().getExtras();
        String ititle = extras.getString("title");
        title.setText(ititle);
        String idiscription = extras.getString("discription");
        discription.setText(idiscription);
        String icontent = extras.getString("content");
        content.setText(icontent);
        String iurl = extras.getString("url");
        String iurlToImage = extras.getString("getUrlToImage");
        if (iurlToImage == null) {
            Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/9/9b/Google_news_logo.png?20210712160907").placeholder(R.drawable.newslogo)
                    .into(imgnews);
        } else{
            Picasso.get().load(iurlToImage)
                    .placeholder(R.drawable.newslogo).into(imgnews);
        }

        visitnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(cards_details.this,webview.class);
               i.putExtra("url",iurl);
               startActivity(i);
            }
        });
    }
}