package com.example.assignment2updated;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.LinkedList;

public class TickerListFragment extends Fragment {

    private TickerViewModel ticker_VM;
    ListView ticker_lv;

    AdapterView.OnItemClickListener ticker_listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ticker_VM.setUrl(ticker_lv.getItemAtPosition(i).toString());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frag_root = inflater.inflate(R.layout.fragment_tickerlist, container, false);
        ticker_lv = frag_root.findViewById(R.id.ticker_LV);
        return frag_root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ticker_VM = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);
        ticker_lv.setOnItemClickListener(ticker_listener);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1);

        ticker_VM.getTickers().observe(getViewLifecycleOwner(), new Observer<LinkedList<String>>() {
            @Override
            public void onChanged(LinkedList<String> tickers) {
                if (tickers != null) {
                    // Update the ArrayAdapter with the new list of tickers
                    adapter.clear();
                    adapter.addAll(tickers);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(requireActivity(), String.valueOf(tickers.size()), Toast.LENGTH_LONG).show();
                }
            }
        });

        // Set the adapter to the ListView
        ticker_lv.setAdapter(adapter);
    }
}
