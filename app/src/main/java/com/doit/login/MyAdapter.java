package com.doit.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<ChatData> list;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvChat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChat = itemView.findViewById(R.id.tvChat);
        }
        public void setItem(C)
    }


    public MyAdapter(ArrayList<ChatData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatData data = list.get(position);
        holder.setItem(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
