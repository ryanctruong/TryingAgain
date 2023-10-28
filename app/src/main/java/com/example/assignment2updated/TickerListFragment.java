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

    private TickerViewModel tickerViewModel;
    private ListView tickerListView;

    private AdapterView.OnItemClickListener onTickerItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            // Clickable URL
            tickerViewModel.setUrl(adapterView.getItemAtPosition(position).toString());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        View fragmentView = inflater.inflate(R.layout.fragment_tickerlist, container, false);
        tickerListView = fragmentView.findViewById(R.id.ticker_list);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tickerViewModel = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);

        // Set the OnClickListener
        tickerListView.setOnItemClickListener(onTickerItemClick);

        // Creating adapter
        final ArrayAdapter<String> tickerListAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_list_item_1);

        // Updating Adapter
        tickerViewModel.getTickersListLiveData().observe(getViewLifecycleOwner(), tickers -> {
            if (tickers != null) {
                // Update
                tickerListAdapter.clear();
                tickerListAdapter.addAll(tickers);
                tickerListAdapter.notifyDataSetChanged();

                // Display Toast
                Toast.makeText(requireActivity(), "Total tickers: " + tickers.size(), Toast.LENGTH_LONG).show();
            }
        });

        // Adapter for ListView
        tickerListView.setAdapter(tickerListAdapter);
    }
}
