package com.example.andre.flash.views.login.finder;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.andre.flash.R;
import com.github.ybq.android.spinkit.SpinKitView;

public class FinderDialogFragment extends DialogFragment implements FinderCallback{
    private EditText editText;
    private ImageButton imageButton;
    private SpinKitView loading;

    public static FinderDialogFragment newInstance() {
        return new FinderDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_finder, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText=view.findViewById(R.id.userinputEt);
        imageButton = view.findViewById(R.id.sendBtn);
        loading=view.findViewById(R.id.loadingSkv);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCancelable(false);
                String email= editText.getText().toString();
                editText.setVisibility(View.GONE);
                imageButton.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                new UserValidation(FinderDialogFragment.this, getContext()).init(email);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }

    @Override
    public void error(String error) {
        restoreViews();
        editText.setError(error);

    }

    @Override
    public void succes() {
        dismiss();

    }

    @Override
    public void notFound() {
        restoreViews();
        Toast.makeText(getContext(), "Ususario no hallado", Toast.LENGTH_SHORT).show();

    }

    private void restoreViews(){
        editText.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        setCancelable(true);
    }
}
