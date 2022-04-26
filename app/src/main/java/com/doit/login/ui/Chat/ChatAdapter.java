package com.doit.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    public LoginActivity activity;
    private List<User> mDataset;
    String stMyEmail = "";
    private Context context;
    private Intent intent;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public ChatAdapter(List<User> mDataset, String stMyEmail, Context context) {
        this.mDataset = mDataset;
        this.stMyEmail = stMyEmail;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView Mmessage, Memail;

        public MyViewHolder(View v) {
            super(v);
            Mmessage = v.findViewById(R.id.chatitem_textview_lastMessage);
            Memail = v.findViewById(R.id.chatitem_textview_title);
        }

        public void setItem(User item) {
            Mmessage.setText(item.getEmail());
            Memail.setText(item.getText());

        }

    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(List<User> myDataset, String stEmail) {
        mDataset = myDataset;
        this.stMyEmail = stEmail;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_chat_menu_item, parent, false);


        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        User item = mDataset.get(position);
        holder.setItem(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), ChatActivity.class);
                view.getContext().startActivity(intent);
                // Toast.makeText(view.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.Mmessage.setText(mDataset.get().getText());
//        holder.Memail.setText(mDataset.get(1).getText());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
