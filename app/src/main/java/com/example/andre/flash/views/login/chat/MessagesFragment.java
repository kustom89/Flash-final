package com.example.andre.flash.views.login.chat;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andre.flash.R;
import com.example.andre.flash.adapters.MessageAdapter;
import com.example.andre.flash.adapters.MessageCallback;
import com.example.andre.flash.main.chats.ChatsFragment;
import com.example.andre.flash.models.Chat;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment implements MessageCallback{

    public MessageAdapter adapter;
    private RecyclerView recyclerView;


    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Chat chat = (Chat) getActivity().getIntent().getSerializableExtra(ChatsFragment.CHAT);


        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(false);


        adapter = new MessageAdapter(getActivity(), chat.getKey(), this);

        recyclerView.setAdapter(adapter);

        //***

    }

    @Override
    public void update() {
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);


    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
