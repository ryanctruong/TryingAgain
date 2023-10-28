package com.example.assignment2updated;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.Objects;

public class InfoWebFragment extends Fragment {

    private WebView infoWebView;
    private TickerViewModel tickerViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View fragmentView = inflater.inflate(R.layout.fragment_infoweb, container, false);
        infoWebView = fragmentView.findViewById(R.id.f_info_web);

        // Enable JavaScript in the WebView
        infoWebView.getSettings().setJavaScriptEnabled(true);

        // Get the TickerViewModel
        tickerViewModel = new ViewModelProvider(getActivity()).get(TickerViewModel.class);

        // Observe changes in the URL LiveData and load it in the WebView
        tickerViewModel.getUrlLiveData().observe(getViewLifecycleOwner(), url -> {
            infoWebView.loadUrl(Objects.requireNonNull(url));
        });

        return fragmentView;
    }
}
