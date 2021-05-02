package com.example.project.retrofitUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.model.reviews;

import java.util.List;

public class reviewAdapter extends RecyclerView.Adapter<reviewAdapter.MyyViewHolder> {

    private List<reviews> fetchedReviews;
    private Context context;

    public reviewAdapter(List<reviews> fetchedReviews, Context context) {
        this.fetchedReviews = fetchedReviews;
        this.context = context;
    }

    @Override
    public MyyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, parent,false);

        return new reviewAdapter.MyyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyyViewHolder holder, int position) {
        holder.usernam.setText(fetchedReviews.get(position).getUsername());
        holder.review.setText(fetchedReviews.get(position).getReview());

    }

    @Override
    public int getItemCount() {
        return fetchedReviews.size();
    }

    public static class MyyViewHolder extends RecyclerView.ViewHolder{

        TextView usernam,review;

        public MyyViewHolder(@NonNull View itemView) {
            super(itemView);

            usernam = itemView.findViewById(R.id.revusername);
            review = itemView.findViewById(R.id.review);
        }
    }
}
