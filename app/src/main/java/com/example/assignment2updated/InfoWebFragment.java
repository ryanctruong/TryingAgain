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

    WebView info_web_wv;
    private TickerViewModel ticker_VM;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frag_root = inflater.inflate(R.layout.fragment_infoweb, container, false);
        info_web_wv = frag_root.findViewById(R.id.info_web_WV);
        info_web_wv.getSettings().setJavaScriptEnabled(true);
        ticker_VM = new ViewModelProvider(getActivity()).get(TickerViewModel.class);
        ticker_VM.getUrlLiveData().observe(getViewLifecycleOwner(), s -> info_web_wv.loadUrl(Objects.requireNonNull(ticker_VM.getUrlLiveData().getValue())));
        return frag_root;
    }
}