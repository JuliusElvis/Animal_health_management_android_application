package com.example.project.retrofitUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.model.registeredDocs;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private List<registeredDocs> regDocs;
    private Context context;
    private RecyclerViewClickListener listener;

    public Adapter(List<registeredDocs> regDocs, Context context, RecyclerViewClickListener listener) {
        this.regDocs = regDocs;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        holder.id.setText(String.valueOf(regDocs.get(position).getId()));
        holder.name.setText(regDocs.get(position).getName());
        holder.address.setText(regDocs.get(position).getAddress());
        holder.qualification.setText(regDocs.get(position).getQualification());

    }

    @Override
    public int getItemCount() {
        return regDocs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, address,qualification,id;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idUser);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            qualification = itemView.findViewById(R.id.qualification);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {
            listener.onClick(itemView, getAbsoluteAdapterPosition());

        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View itemView,int position);
    }
}
