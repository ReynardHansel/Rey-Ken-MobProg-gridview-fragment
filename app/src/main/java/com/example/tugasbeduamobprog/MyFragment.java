package com.example.tugasbeduamobprog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    private GridView gridView;
    private ArrayAdapter<String> adapter;
    private OnDataTransferListener listener;

    public MyFragment() {
        // Required empty public constructor
    }

    public void setOnDataTransferListener(OnDataTransferListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        gridView = rootView.findViewById(R.id.grid_view);

        // Example data
        List<String> data = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "UwU", "OwO");
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, data);
        gridView.setAdapter(adapter);

        // Set click listener to send data to activity when an item is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                transferData(selectedItem);
            }
        });

        return rootView;
    }

    // Call this method when you want to transfer data
    private void transferData(String data) {
        if (listener != null) {
            listener.onDataTransfer(data);
        }
    }

    // Define an interface for data transfer listener
    public interface OnDataTransferListener {
        void onDataTransfer(String data);
    }
}