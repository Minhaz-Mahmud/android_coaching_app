package com.example.betaapp.Show;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.betaapp.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    Context context;
    List<item> postList;

    public PostAdapter(Context context , List<item> postList){
        this.context = context;
        this.postList = postList;
    }
    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.eachpost, parent , false);
        return new PostHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        item i = postList.get(position);
        holder.setImageView(i.getImageUrl());
        holder.setmName(i.getName());
        holder.setmEdu(i.getEducation());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView mName, mEdu;
        View view;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

        }

        public void setImageView(String url){
            imageView = view.findViewById(R.id.imageview);
            Glide.with(context).load(url).into(imageView);
        }
        public void setmName(String name){
            mName = view.findViewById(R.id.name);
            mName.setText("Name: "+name);
        }
        public void setmEdu(String edu){
            mEdu=view.findViewById(R.id.education);
            mEdu.setText("Education: "+edu);
        }
    }
}