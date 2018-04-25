package com.example.andre.flash;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CurrentUser {

    private FirebaseUser currenteUser = FirebaseAuth.getInstance().getCurrentUser();

    public FirebaseUser getCurrenteUser() {
        return currenteUser;
    }
}
