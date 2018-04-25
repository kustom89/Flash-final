package com.example.andre.flash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;

import java.util.Arrays;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 343;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        if(new CurrentUser().getCurrenteUser()!=null){
            logger();
        }else{
            singUp();
        }




    }
    private void singUp(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(

                                new AuthUI.IdpConfig.EmailBuilder().build() ,
                                new AuthUI.IdpConfig.GoogleBuilder().build()/*,
                                new AuthUI.IdpConfig.PhoneBuilder().build(),
                                new AuthUI.IdpConfig.FacebookBuilder().build(),
                                new AuthUI.IdpConfig.TwitterBuilder().build()*/))
                        .setIsSmartLockEnabled(!BuildConfig.DEBUG, true)
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(RC_SIGN_IN == requestCode){
            if(RESULT_OK== resultCode){
                logger();
            }
        }
    }

    private void logger(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
