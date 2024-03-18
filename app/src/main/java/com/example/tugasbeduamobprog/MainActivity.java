package com.example.tugasbeduamobprog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        gridView.setAdapter(adapter);

        MyFragment fragment = new MyFragment();
        fragment.setOnDataTransferListener(this);
        // Add your fragment to your activity
    }

    @Override
    public void onDataTransfer(String data) {
        // Handle the data received from the fragment
        dataList.add(data);
        adapter.notifyDataSetChanged();
    }
}