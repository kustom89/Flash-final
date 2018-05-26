package com.example.andre.flash.adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andre.flash.R;
import com.example.andre.flash.data.CurrentUser;
import com.example.andre.flash.data.Nodes;
import com.example.andre.flash.models.Message;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MessageAdapter extends FirebaseRecyclerAdapter<Message, MessageAdapter.MessageHolder> {

    private MessageCallback callback;
    private static final String CURRENT_EMAIL = new CurrentUser().email();

    public MessageAdapter(LifecycleOwner lifecycleOwner, String chatId, MessageCallback callback) {
        super(new FirebaseRecyclerOptions.Builder<Message>()
                .setQuery(new Nodes().messages(chatId), Message.class)
                .setLifecycleOwner(lifecycleOwner)
                .build());

        this.callback = callback;
    }

    @Override
    protected void onBindViewHolder(@NonNull MessageHolder holder, int position, @NonNull Message model) {

        TextView textView = (TextView) holder.itemView;
        if (CURRENT_EMAIL.equals(model.getOwner())) {
            textView.setGravity(Gravity.RIGHT);
        } else {
            textView.setGravity(Gravity.LEFT);
        }

        textView.setText(model.getContent());



    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        callback.update();
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_message,parent,false);
        return new MessageHolder(view);
    }

    public static class MessageHolder extends RecyclerView.ViewHolder {
        public MessageHolder(View itemView) {

            super(itemView);
        }
    }

}
