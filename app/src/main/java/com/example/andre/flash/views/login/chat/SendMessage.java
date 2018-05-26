package com.example.andre.flash.views.login.chat;

import com.example.andre.flash.data.CurrentUser;
import com.example.andre.flash.data.Nodes;
import com.example.andre.flash.models.Chat;
import com.example.andre.flash.models.Message;

public class SendMessage {

    public void validateMessage(String message, Chat chat){
        if(message.trim().length()>0){
            String email= new CurrentUser().email();
            Message msg= new Message();
            msg.setContent(message);
            msg.setOwner(email);

            String key = chat.getKey();
            new Nodes().messages(chat.getKey()).push().setValue(msg);
            new Nodes().userChat(chat.getUid()).child(key).child("notification").setValue(true);

        }
    }
}
