package com.example.andre.flash.drawer;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.andre.flash.data.CurrentUser;
import com.example.andre.flash.data.EmailProcessor;
import com.example.andre.flash.data.Nodes;
import com.example.andre.flash.data.PhotoPreference;
import com.example.andre.flash.models.LocalUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadPhoto {
    private Context context;

    public UploadPhoto(Context context) {
        this.context = context;
    }

    public void toFirebase(String path){

        final CurrentUser currentUser= new CurrentUser();
        String folder= new EmailProcessor().sanitizedEmail(currentUser.email() + "/");
        String photoName = "avatar.jpeg";
        String baseUrl="gs://flash-84da6.appspot.com/avatars/";
        String refUrl = baseUrl + folder + photoName;
        StorageReference storageReference= FirebaseStorage.getInstance().getReferenceFromUrl(refUrl);
        storageReference.putFile(Uri.parse(path)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String[] fullUrl=taskSnapshot.getDownloadUrl().toString().split("&token");
                String url= fullUrl[0];

                new PhotoPreference(context).photoSave(url);

                LocalUser user = new LocalUser();
                user.setEmail(currentUser.email());
                user.setName(currentUser.getCurrenteUser().getDisplayName());
                user.setPhoto(url);
                user.setUid(currentUser.uid());
                String key = new EmailProcessor().sanitizedEmail(currentUser.email());
                new Nodes().user(key).setValue(user);
                Log.d("user", user.getName());
                FirebaseDatabase.getInstance().getReference().child("users").child(key).setValue(user);
            }
        });

    }
}
