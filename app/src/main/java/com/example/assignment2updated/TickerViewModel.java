package com.example.assignment2updated;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TickerViewModel extends ViewModel {

    private MutableLiveData<String> currentUrl;
    private MutableLiveData<ArrayList<String>> tickers;

    public MutableLiveData<ArrayList<String>> getTickersListLiveData() {
        if (tickers == null) {
            tickers = new MutableLiveData<>();
            ArrayList<String> list = new ArrayList<>();
            list.add("BAC");
            list.add("AAPL");
            list.add("DIS");
            tickers.setValue(list);
        }
        return tickers;
    }

    public void addTicker(String ticker) {
        ArrayList<String> list = tickers.getValue();
        assert list != null;
        if (list.size() >= 6) {
            list.remove(list.size() - 1);
        }
        list.add(ticker);
        tickers.setValue(list);
    }

    public MutableLiveData<String> getUrlLiveData() {
        if (currentUrl == null) {
            currentUrl = new MutableLiveData<>();
            String defaultUrl = "https://seekingalpha.com/";
            currentUrl.setValue(defaultUrl);
        }
        return currentUrl;
    }

    public void setUrl(String url) {
        if (currentUrl == null) {
            currentUrl = new MutableLiveData<>();
        }
        currentUrl.setValue("https://seekingalpha.com/symbol/" + url);
    }
}
