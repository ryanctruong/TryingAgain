package com.example.assignment2updated;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TickerViewModel extends ViewModel {

    private MutableLiveData<String> currentUrlLiveData;
    private MutableLiveData<ArrayList<String>> tickersListLiveData;

    // Get a LiveData object
    public MutableLiveData<ArrayList<String>> getTickersListLiveData() {
        if (tickersListLiveData == null) {
            tickersListLiveData = new MutableLiveData<>();
            ArrayList<String> initialTickers = new ArrayList<>();
            initialTickers.add("BAC");
            initialTickers.add("AAPL");
            initialTickers.add("DIS");
            tickersListLiveData.setValue(initialTickers);
        }
        return tickersListLiveData;
    }

    // Add a new ticker to the list
    public void addTicker(String ticker) {
        ArrayList<String> tickerList = tickersListLiveData.getValue();
        assert tickerList != null;
        if (tickerList.size() >= 6) {
            tickerList.remove(tickerList.size() - 1);
        }
        tickerList.add(ticker);
        tickersListLiveData.setValue(tickerList);
    }

    // Current URL
    public MutableLiveData<String> getUrlLiveData() {
        if (currentUrlLiveData == null) {
            currentUrlLiveData = new MutableLiveData<>();
            String defaultUrl = "https://seekingalpha.com/";
            currentUrlLiveData.setValue(defaultUrl);
        }
        return currentUrlLiveData;
    }

    // Set the current URL
    public void setUrl(String url) {
        if (currentUrlLiveData == null) {
            currentUrlLiveData = new MutableLiveData<>();
        }
        currentUrlLiveData.setValue("https://seekingalpha.com/symbol/" + url);
    }
}
