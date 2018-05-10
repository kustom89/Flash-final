package com.example.andre.flash.drawer;

import android.content.Context;

import com.example.andre.flash.data.PhotoPreference;

public class PhotoValidation {

    private Context context;
    private PhotoCallback callback;

    public PhotoValidation(Context context, PhotoCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void validate(){
        String url=new PhotoPreference(context).getPhoto();
        if (url != null){

            callback.photoAviable(url);
        }else{
            callback.emptyPhoto();
        }
    }
}
