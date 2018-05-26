package com.example.andre.flash.adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andre.flash.R;
import com.example.andre.flash.data.CurrentUser;
import com.example.andre.flash.data.Nodes;
import com.example.andre.flash.models.Chat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.siyamed.shapeimageview.BubbleImageView;
import com.squareup.picasso.Picasso;

public class ChatsAdapter extends FirebaseRecyclerAdapter<Chat, ChatsAdapter.ChatHolder> {

    private  ChatListener listener;


    public ChatsAdapter(LifecycleOwner lifecycleOwner, ChatListener listener) {
        super(new FirebaseRecyclerOptions.Builder<Chat>()
        .setQuery(new Nodes().userChat(new CurrentUser().uid()),Chat.class)
                .setLifecycleOwner(lifecycleOwner)
                .build()
        );
        this.listener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull final ChatHolder holder, final int position, @NonNull final Chat model) {
        Picasso.get().load(model.getPhoto())
                .centerCrop()
                .fit()
                .into(holder.photo);

        holder.email.setText(model.getReceiver());
        if(model.isNotification()) {
            holder.notification.setVisibility(View.VISIBLE);
        }else{
            holder.notification.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat auxChat =  getItem(holder.getAdapterPosition());
                listener.clicked(auxChat);
                //auxChat.getKey(),auxChat.getReceiver()

                Log.d("TIMESTAMP", String.valueOf(model.getTimestamp()));

            }
        });


    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat, parent, false);
        return new ChatsAdapter.ChatHolder(view);
    }

    public  static class ChatHolder extends RecyclerView.ViewHolder {

        private BubbleImageView photo;
        private TextView email;
        private View notification;

        public ChatHolder(View itemView) {
            super(itemView);
            photo= itemView.findViewById(R.id.photoBiv);
            email= itemView.findViewById(R.id.emailTv);
            notification= itemView.findViewById(R.id.notificationV);
        }
    }
}
