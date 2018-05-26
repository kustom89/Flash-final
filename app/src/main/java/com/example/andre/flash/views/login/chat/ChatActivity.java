package com.example.andre.flash.views.login.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.andre.flash.R;
import com.example.andre.flash.data.CurrentUser;
import com.example.andre.flash.data.Nodes;
import com.example.andre.flash.main.chats.ChatsFragment;
import com.example.andre.flash.models.Chat;
import com.google.firebase.database.ServerValue;

public class ChatActivity extends AppCompatActivity {

    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Chat chat = (Chat) getIntent().getSerializableExtra(ChatsFragment.CHAT);
        key = chat.getKey();
        new Nodes().userChat(new CurrentUser().uid()).child(key).child("timestamp").setValue(ServerValue.TIMESTAMP);
        String email = getIntent().getStringExtra(ChatsFragment.CHAT);

        getSupportActionBar().setTitle(chat.getReceiver());


    }

    @Override
    protected void onPause() {
        super.onPause();
        new Nodes().userChat(new CurrentUser().uid()).child(key).child("notificacion").setValue(false);
    }
}

