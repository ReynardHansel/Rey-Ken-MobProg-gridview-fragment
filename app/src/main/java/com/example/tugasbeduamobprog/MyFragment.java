package com.example.tugasbeduamobprog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
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
    private List<String> dataList = new ArrayList<>();

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

        // Initialize the adapter here
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, dataList);
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

    public void addData(String data) {
        Log.d("MyFragment", "Adding data: " + data);
        dataList.add(data);
        // Check if adapter is not null before calling notifyDataSetChanged
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            Log.e("MyFragment", "Adapter is null. Cannot update UI.");
        }
        Log.d("MyFragment", "Data added. GridView updated.");
    }

    public void updateData(List<String> data) {
        // Update your adapter with the new data
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    // Call this method when you want to transfer data
    public void transferData(String data) {
        // if (listener != null) {
        //     listener.onDataTransfer(data);
        // }

        dataList.add(data);
        // Check if adapter is not null before calling notifyDataSetChanged
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            Log.e("MyFragment", "Adapter is null. Cannot update UI.");
        }
    }

    // Define an interface for data transfer listener
    public interface OnDataTransferListener {
        void onDataTransfer(String data);
    }
}
