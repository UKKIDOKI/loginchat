//package com.doit.login;
//
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
//    private ArrayList<ChatList> mDataset;
//    String stMyEmail = "";
//    String stImage = "";
//
//    public ChatAdapter(ArrayList<ChatList> myDataset, String stEmail) {
//        mDataset = myDataset;
//        this.stMyEmail = stEmail;
//    }
//
//    // Provide a reference to the views for each data item
//    // Complex data items may need more than one view per item, and
//    // you provide access to all the views for a data item in a view holder
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        // each data item is just a string in this case
//        public TextView tv_name;
//        public ImageView iv_user;
//        public TextView tv_message;
//
//        public MyViewHolder(View v) {
//            super(v);
//            tv_name = v.findViewById(R.id.tv_name);
//            tv_message = v.findViewById(R.id.iv_user);
//            iv_user = v.findViewById(R.id.iv_user);
//
//        }
//    }
//
//
//
//    // Provide a suitable constructor (depends on the kind of dataset)
//    public ChatAdapter(ArrayList<ChatList> myDataset, String stEmail, String stImage) {
//        mDataset = myDataset;
//        this.stMyEmail = stEmail;
//        this.stImage = stImage;
//    }
//
//    // Create new views (invoked by the layout manager)
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent,
//                                                     int viewType) {
//        // create a new view
//        View v =  LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_chat_menu_item, parent, false);
//
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.tv_name.setText(mDataset.get(position).getText());
//        holder.tv_message.setText(mDataset.get(position).getText());
//        holder.iv_user.setImageURI(Uri.parse(mDataset.get(position).getImage()));
//
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return mDataset.size();
//    }
//}
