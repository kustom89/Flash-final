package com.example.andre.flash.views.login.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.andre.flash.R;
import com.example.andre.flash.main.chats.ChatsFragment;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String key = getIntent().getStringExtra(ChatsFragment.CHAT_KEY);
        String email = getIntent().getStringExtra(ChatsFragment.CHAT_RECEIVER);
        getSupportActionBar().setTitle(email);

    }
        }

