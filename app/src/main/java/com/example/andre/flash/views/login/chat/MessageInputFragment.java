package com.example.andre.flash.views.login.chat;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.andre.flash.R;
import com.example.andre.flash.main.chats.ChatsFragment;
import com.example.andre.flash.models.Chat;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageInputFragment extends Fragment {


    public MessageInputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_input, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final Chat chat = (Chat) getActivity().getIntent().getSerializableExtra(ChatsFragment.CHAT);


        final EditText editText = view.findViewById(R.id.userinputEt);
        editText.setHint("Escriba un mensaje");

        ImageButton imageButton = view.findViewById(R.id.sendBtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SendMessage().validateMessage(editText.getText().toString(),chat);
                editText.setText("");
            }
        });


    }
}
