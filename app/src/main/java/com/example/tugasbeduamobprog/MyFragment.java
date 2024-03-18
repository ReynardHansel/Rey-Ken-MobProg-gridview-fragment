package com.example.tugasbeduamobprog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GridView gridView;
    private ArrayAdapter<String> adapter;
    private OnDataTransferListener listener;

    public MyFragment() {
        // Required empty public constructor
    }

    public void setOnDataTransferListener(OnDataTransferListener listener) {
        this.listener = listener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        gridView = rootView.findViewById(R.id.grid_view);

        // Example data
        List<String> data = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, data);
        gridView.setAdapter(adapter);

        return rootView;
    }

    public interface OnDataTransferListener {
        void onDataTransfer(String data);
    }

    // Call this method when you want to transfer data
    public void transferData(String data) {
        if (listener != null) {
            listener.onDataTransfer(data);
        }
    }
}