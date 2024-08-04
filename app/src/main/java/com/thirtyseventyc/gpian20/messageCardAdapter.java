package com.thirtyseventyc.gpian20;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gpian20.R;

import java.util.ArrayList;
import java.util.List;



public class messageCardAdapter extends RecyclerView.Adapter<messageCardAdapter.MessageCardViewHolder> {

    public SharedPreferences sharedPref;
    String name;
    private List<messageCardModel> messageCardList;
    private static List<String> username=new ArrayList<>(1000000);
    int pos=0;
    public messageCardAdapter(List<messageCardModel> messageCardList, Context context) {
        this.messageCardList = messageCardList;
        sharedPref = context.getSharedPreferences("MyData", MODE_PRIVATE);
        name = sharedPref.getString("name", null);
        this.messageCardList = messageCardList;
    }

    public static void getUsername(List<String> usernames){
        username=usernames;
    }

    @Override
    public int getItemViewType(int position) {

        messageCardModel messageCard = messageCardList.get(position);
        String username = messageCard.getUsername();
        return username.equals(name) ? 0 : 1;
    }


    @NonNull
    @Override
    public MessageCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_card_sender, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_card, parent, false);
        }
        return new MessageCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageCardViewHolder holder, int position) {
        messageCardModel MessageCardModel = messageCardList.get(position);
//                int isDeveloper=getItemViewType(position);
//                if(isDeveloper==0){
//                        holder.developerCard.setCardBackgroundColor(Color.rgb(255,193,7));
//                        holder.developerCard.setRadius(20);
//                }
//                else {
//                        holder.developerCard.setCardBackgroundColor(Color.rgb(34,39,43));
//                }
        if(MessageCardModel.getUsername().equals("Samay") || MessageCardModel.getUsername().equals("sarthak") ){
            holder.developerCard.setCardBackgroundColor(Color.rgb(255,193,7));
        //    holder.developerCard.setRadius(30);
        }
        else {
            holder.developerCard.setCardBackgroundColor(Color.rgb(34,39,43));
        }
        holder.usernameTextView.setText(MessageCardModel.getUsername());
        holder.messageTextView.setText(MessageCardModel.getMessage());
        holder.userimage.setImageResource(R.drawable.user);
//                holder.timeStamp.setText(Calendar.getInstance().getTime().toString());
        holder.timeStamp.setText(MessageCardModel.getTime());
    }

    @Override
    public int getItemCount() {
        return messageCardList.size();
    }

    public static class MessageCardViewHolder extends RecyclerView.ViewHolder {
        public TextView usernameTextView;
        public TextView messageTextView;
        public ImageView userimage;
        public TextView timeStamp;
        public CardView developerCard;

        public MessageCardViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username);
            messageTextView = itemView.findViewById(R.id.message);
            userimage=itemView.findViewById(R.id.userimage);
            timeStamp=itemView.findViewById(R.id.timeStamp);
            developerCard=itemView.findViewById(R.id.developerCard);
            developerCard.setCardBackgroundColor(Color.rgb(34,39,43));
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_card, parent, false);
            }
            return convertView;
        }
    }

}

