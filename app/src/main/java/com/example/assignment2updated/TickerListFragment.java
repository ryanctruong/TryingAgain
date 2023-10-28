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
import androidx.lifecycle.ViewModelProvider;

public class TickerListFragment extends Fragment {

    private TickerViewModel tickerVM;
    ListView tickerListView;

    AdapterView.OnItemClickListener tickerListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            tickerVM.setUrl(tickerListView.getItemAtPosition(i).toString());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragRoot = inflater.inflate(R.layout.fragment_tickerlist, container, false);
        tickerListView = fragRoot.findViewById(R.id.ticker_LV);
        return fragRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tickerVM = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);
        tickerListView.setOnItemClickListener(tickerListener);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1);

        tickerVM.getTickersListLiveData().observe(getViewLifecycleOwner(), tickers -> {
            if (tickers != null) {
                adapter.clear();
                adapter.addAll(tickers);
                adapter.notifyDataSetChanged();
                Toast.makeText(requireActivity(), String.valueOf(tickers.size()), Toast.LENGTH_LONG).show();
            }
        });

        // Set the adapter to the ListView
        tickerListView.setAdapter(adapter);
    }
}
