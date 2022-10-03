package com.example.mintnews.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mintnews.R;
import com.squareup.picasso.Picasso;

public class FullNewsActivity extends AppCompatActivity {

    private TextView newsTitle, newsDescription, newsContent;
    private ImageView newsImage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_news);

        newsTitle = findViewById(R.id.newsTitle);
        newsDescription = findViewById(R.id.newsDescription);
        newsContent = findViewById(R.id.newsContent);
        button = findViewById(R.id.fullStoryBtn);

        newsImage = findViewById(R.id.newsImage);


        // setting text

//        Log.d("SHWETA", getIntent().getStringExtra("title"));
//        Log.d("SHWETA", getIntent().getStringExtra("desc"));
//        Log.d("SHWETA", getIntent().getStringExtra("content"));
//        Log.d("SHWETA", getIntent().getStringExtra("imageUrl"));
//        Log.d("SHWETA", getIntent().getStringExtra("url"));

        // SETTING VIEWS NOW

        newsTitle.setText(getIntent().getStringExtra("title"));
        newsDescription.setText(getIntent().getStringExtra("desc"));
        newsContent.setText(getIntent().getStringExtra("content"));

        Picasso.get().load(getIntent().getStringExtra("imageUrl")).into(newsImage);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getIntent().getStringExtra("url")));
            startActivity(intent);
        });



    }
}