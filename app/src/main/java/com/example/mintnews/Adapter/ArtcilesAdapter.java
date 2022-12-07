package com.example.mintnews.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mintnews.Activities.FullNewsActivity;
import com.example.mintnews.Models.ArticlesModel;
import com.example.mintnews.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArtcilesAdapter extends RecyclerView.Adapter<ArtcilesAdapter.ViewHolder> {

    public ArtcilesAdapter(Context context, ArrayList<ArticlesModel> articlesModelArrayList) {
        this.context = context;
        this.articlesModelArrayList = articlesModelArrayList;
    }


    private Context context;
    private ArrayList<ArticlesModel> articlesModelArrayList;

    @NonNull
    @Override
    public ArtcilesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.articles_recycler_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtcilesAdapter.ViewHolder holder, int position) {

        ArticlesModel model = articlesModelArrayList.get(position);
        holder.newsTitle.setText(model.getTitle());
//        holder.sourceLabel.setText(model.getSourceModel().getName());
//        Log.d("HAPPIE", model.getSourceModel().getName());

        Picasso.get().load(model.getUrlToImage())
//                .resize(90,130)
                .into(holder.newsImage, new Callback() {
            @Override
            public void onSuccess() {
//                Toast.makeText(context, "Succsess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Log.d("SHAMBHUJI", e.getLocalizedMessage());
            }
        });

        // ON CLICK LISTENER

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullNewsActivity.class);
                intent.putExtra("title", model.getTitle());
                intent.putExtra("imageUrl", model.getUrlToImage());
                intent.putExtra("content", model.getContent());
                intent.putExtra("desc", model.getDescription());
                intent.putExtra("url", model.getUrl());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView newsImage;
        private TextView newsTitle, sourceLabel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsImage = itemView.findViewById(R.id.newsImage);
            sourceLabel = itemView.findViewById(R.id.sourceLabel);

        }
    }
}
