package com.example.tugasbeduamobprog;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private TextInputEditText input;
    private Button submitButton;
    private MyFragment.OnDataTransferListener onDataTransferListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onDataTransferListener = (MyFragment.OnDataTransferListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnDataTransferListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        input = rootView.findViewById(R.id.input);
        submitButton = rootView.findViewById(R.id.submit_btn); // Replace with your actual button ID

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = Objects.requireNonNull(input.getText()).toString();
                Log.d("HomeFragment", "Button clicked. Input text: " + text);
                if (onDataTransferListener != null) {
                    Log.d("HomeFragment", "Sending data to MainActivity");
                    onDataTransferListener.onDataTransfer(text);
                }
            }
        });

        return rootView;
    }
}