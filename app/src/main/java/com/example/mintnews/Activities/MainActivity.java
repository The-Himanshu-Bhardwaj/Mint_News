package com.example.mintnews.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mintnews.Adapter.ArtcilesAdapter;
import com.example.mintnews.Models.ArticlesModel;
import com.example.mintnews.Models.NewsModel;
import com.example.mintnews.Models.SourceModel;
import com.example.mintnews.R;
import com.example.mintnews.Retrofit.RetrofitWebService;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitWebService retrofitWebService;
    private ArrayList<ArticlesModel> articlesList;
    //
    private ArrayList<SourceModel> sourceModelArrayList;
    private ArtcilesAdapter artcilesAdapter;
    private RecyclerView recyclerView;

    private TextView categoryLabel;

    ShimmerFrameLayout shimmerFrameLayout;

    private final String API_KEY = "8b160bca2e2f462196fa315230dd5b38";

    private ImageView allNewsBtn, entertainNewsBtn, businessNewsBtn, healthNewsBtn,
            sportsNewsBtn, technologyNewsBtn, scienceNewsBtn;


    private final String ALL_NEWS_URL = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey="+API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
//        showSnackbar();

        categoryLabel = findViewById(R.id.categoryLabel);


        retrofitWebService = RetrofitWebService.retrofit.create(RetrofitWebService.class);

        recyclerView = findViewById(R.id.articlesRecyclerView);

        articlesList = new ArrayList<>();
        //
        sourceModelArrayList = new ArrayList<>();

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
                Log.d("herror+", response.body().toString());

                NewsModel model = response.body();

                ArrayList<ArticlesModel> articles = model.getArticles();

                for (int i = 0; i < articles.size(); i++) {
                    articlesList.add(new ArticlesModel(
                            articles.get(i).getSourceModel(),
                            articles.get(i).getTitle(),
                            articles.get(i).getDescription(),
                            articles.get(i).getContent(),
                            articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl()));

                }


                try {
                    Log.d("happie", articles.get(1).getSourceModel().getName());
                } catch (Exception e) {
                    Log.d("herror", e.getLocalizedMessage());
                }
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
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
            categoryLabel.setText("General");


        });

        entertainNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("entertainment");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
            categoryLabel.setText("Entertainment");
        });

        scienceNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("science");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
            categoryLabel.setText("Science");
        });
        healthNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("health");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
            categoryLabel.setText("Health");
        });
        sportsNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("sports");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
            categoryLabel.setText("Sports");
        });
        technologyNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("technology");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
            categoryLabel.setText("Technology");
        });
        businessNewsBtn.setOnClickListener(view -> {
            getNewsByCategory("business");
            artcilesAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(0);
            categoryLabel.setText("Business");
        });
    }

    private void getNewsByCategory(String category) {
        recyclerView.setVisibility(View.INVISIBLE);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
        Call<NewsModel> call = retrofitWebService.getNewsByCategory("https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey="+API_KEY);
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
//                Toast.makeText(MainActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                NewsModel model = response.body();
                ArrayList<ArticlesModel> articles = model.getArticles();
                articlesList.clear();
                for (int i = 0; i < articles.size(); i++) {
                    articlesList.add(new ArticlesModel(articles.get(i).getSourceModel(),
                            articles.get(i).getTitle(),
                                    articles.get(i).getDescription(),
                                    articles.get(i).getContent(),
                                    articles.get(i).getUrlToImage(),
                                    articles.get(i).getUrl()));


                }
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                artcilesAdapter.notifyDataSetChanged();
//                Toast.makeText(MainActivity.this, category+" news", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public void onBackPressed() {
        showSnackbar();
    }


    private void showSnackbar() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Exit the application ?", Snackbar.LENGTH_LONG)
                .setAction("YES", view -> {
                        finish();
                    Toast.makeText(MainActivity.this, "Thank you for using the app !", Toast.LENGTH_SHORT).show();
                }).setActionTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
        snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.greyy));
        snackbar.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}