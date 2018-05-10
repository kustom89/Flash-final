package com.example.andre.flash.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CurrentUser {

    private FirebaseUser currenteUser = FirebaseAuth.getInstance().getCurrentUser();

    public FirebaseUser getCurrenteUser() {
        return currenteUser;
    }

    public String email(){
        return getCurrenteUser().getEmail();
    }

    public String ui(){
       return currenteUser.getUid();
    }

    public String sanitizedEmail(String email){
        return  email.replace("@","AT").replace(".","DOT");
    }

}