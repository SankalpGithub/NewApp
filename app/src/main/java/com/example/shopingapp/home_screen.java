package com.example.shopingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class home_screen extends AppCompatActivity {

    //"edc3ca6e886f414aa714a20ba87708b6";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Articles> articleArrayList;
    EditText search_bar;
    ImageView imageView;
    ImageButton sidenav;
    String search = "";
    ProgressBar progressBar;
    TextView animation;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        imageView = findViewById(R.id.newsimg);
        search_bar = findViewById(R.id.search_bar);
        progressBar = findViewById(R.id.progressBar);
        sidenav = findViewById(R.id.side_nav);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar =findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(home_screen.this, "hiiiiiii", Toast.LENGTH_SHORT).show();
                switch (item.getItemId()){
                    case R.id.category:
                        Toast.makeText(home_screen.this, "clicked", Toast.LENGTH_SHORT).show();
                    case R.id.country:
                        Toast.makeText(home_screen.this, "clicked", Toast.LENGTH_SHORT).show();
                    case R.id.lgoin:
                        Toast.makeText(home_screen.this, "clicked", Toast.LENGTH_SHORT).show();
                    case R.id.nav_settings:
                        Toast.makeText(home_screen.this, "clicked", Toast.LENGTH_SHORT).show();
                    case R.id.nav_logout:
                        Toast.makeText(home_screen.this, "clicked", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });



        //edc3ca6e886f414aa714a20ba87708b6
        articleArrayList = new ArrayList<>();
        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerview);

        // Set layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Set adapter
        adapter = new recyclerViewAdapter(articleArrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if (i3 < i7) {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerView.smoothScrollToPosition(
                                    recyclerView.getAdapter().getItemCount() - 1);
                        }
                    }, 100);
                }
            }
        });
        getnews("");
        adapter.notifyDataSetChanged();
        button();

        //animation
        animation = findViewById(R.id.spinner);
        String letter = "Welcome To My News App";
        String first = "";
        String finalFirst = "";
        // letter.length() = 22
        for (int i = 0; i < letter.length(); i++) {
//                    if (i < letter.length()){
            first += String.valueOf(letter.charAt(i));
            finalFirst = first;
//                    }else {
//                        letter = letter.substring(0,letter.length()-1);
//                        Log.d("newstags", String.valueOf(letter.length()));
//                        finalFirst=String.valueOf(letter);
//                    }
            String finalFirst1 = finalFirst;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    animation.setText(finalFirst1);
                }
            }, 200 + (i * 200));
        }
    }

    public void button() {
        search_bar.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    search = String.valueOf(search_bar.getText());
                    getnews(search);
                    return true;
                }
                return false;
            }
        });

//        //spinner
//        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.planets_array, R.drawable.spinner_items);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(R.drawable.spinner_items);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String item;
//                if ( adapterView.getItemAtPosition(i).toString()=="All"){
//                    item = "";
//                }else {
//                    item = adapterView.getItemAtPosition(i).toString();
//                }
//                search= item;
//                getnews(search);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
    }

    private void getnews(String search) {
        progressBar.setVisibility(View.VISIBLE);
        articleArrayList.clear();
        String url;
        String surl;
//
        surl = "https://newsapi.org/v2/everything?q=" + search + "&apiKey=edc3ca6e886f414aa714a20ba87708b6";
        url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=edc3ca6e886f414aa714a20ba87708b6";
        String base_url = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<newsmodal> call = null;
        if (!search.equals("")) {
            call = retrofitAPI.getAllNews(surl);
        } else {
            try {
                call = retrofitAPI.getAllNews(url);
            } catch (Exception e) {
                Toast.makeText(this, "NO NEWS", Toast.LENGTH_SHORT).show();
            }
        }
        call.enqueue(new Callback<newsmodal>() {
            @Override
            public void onResponse(Call<newsmodal> call, Response<newsmodal> response) {
                progressBar.setVisibility(View.GONE);
                newsmodal newsmodal = response.body();
                String status = newsmodal.getStatus();
                int totalResults = newsmodal.getTotalResults();
                ArrayList<Articles> articles = newsmodal.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    articleArrayList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(),
                            articles.get(i).getUrlToImage(), articles.get(i).getUrl(), articles.get(i).getContent()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<newsmodal> call, Throwable t) {
                Toast.makeText(home_screen.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}