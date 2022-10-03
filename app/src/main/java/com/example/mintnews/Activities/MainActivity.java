package com.example.mintnews.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mintnews.Adapter.ArtcilesAdapter;
import com.example.mintnews.Models.ArticlesModel;
import com.example.mintnews.Models.NewsModel;
import com.example.mintnews.R;
import com.example.mintnews.Retrofit.RetrofitWebService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitWebService retrofitWebService;
    private ArrayList<ArticlesModel> articlesList;
    private ArtcilesAdapter artcilesAdapter;
    private RecyclerView recyclerView;

    private final String API_KEY = "8b160bca2e2f462196fa315230dd5b38";

    private ImageView allNewsBtn, entertainNewsBtn, businessNewsBtn, healthNewsBtn,
            sportsNewsBtn, technologyNewsBtn, scienceNewsBtn;


    private final String ALL_NEWS_URL = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey="+API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SHIV", "initCategoryBtns: ");


        retrofitWebService = RetrofitWebService.retrofit.create(RetrofitWebService.class);

        recyclerView = findViewById(R.id.articlesRecyclerView);

        articlesList = new ArrayList<>();

        initCategoryBtns();


        artcilesAdapter = new ArtcilesAdapter(this, articlesList);

        recyclerView.setAdapter(artcilesAdapter);


        getAllNews();
        artcilesAdapter.notifyDataSetChanged();

    }


    private void getAllNews() {
        Call<NewsModel> call = retrofitWebService.getAllNews(ALL_NEWS_URL);
        articlesList.clear();
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel model = response.body();
//                Toast.makeText(MainActivity.this, "not null", Toast.LENGTH_SHORT).show();
                ArrayList<ArticlesModel> articles = model.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    articlesList.add(new ArticlesModel(articles.get(i).getTitle(),
                            articles.get(i).getDescription(),
                            articles.get(i).getContent(),
                            articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl()));
//                    Toast.makeText(MainActivity.this, articles.get(i).getUrlToImage() , Toast.LENGTH_SHORT).show();

                }
                artcilesAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initCategoryBtns() {
        allNewsBtn = findViewById(R.id.allNewsBtn);
        entertainNewsBtn = findViewById(R.id.entertainNewsBtn);
        scienceNewsBtn = findViewById(R.id.scienceNewsBtn);
        healthNewsBtn = findViewById(R.id.healthNewsBtn);
        sportsNewsBtn = findViewById(R.id.sportsNewsBtn);
        technologyNewsBtn = findViewById(R.id.techNewsBtn);
        businessNewsBtn = findViewById(R.id.businessNewsBtn);

        allNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("general");
            Log.d("SHIV", "initCategoryBtns: ");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);

        });

        entertainNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("entertainment");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
        });

        scienceNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("science");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
        });
        healthNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("health");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
        });
        sportsNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("sports");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
        });
        technologyNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("technology");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
        });
        businessNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("business");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
        });
    }

    private void getNewsByCategory(String category) {
        Call<NewsModel> call = retrofitWebService.getNewsByCategory("https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey="+API_KEY);
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                NewsModel model = response.body();
                ArrayList<ArticlesModel> articles = model.getArticles();
                articlesList.clear();
                for (int i = 0; i < articles.size(); i++) {
                    articlesList.add(new ArticlesModel(articles.get(i).getTitle(),
                            articles.get(i).getDescription(),
                            articles.get(i).getContent(),
                            articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl()));


                }
                artcilesAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, category+" news", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}