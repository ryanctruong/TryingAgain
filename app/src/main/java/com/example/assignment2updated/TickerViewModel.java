package com.example.assignment2updated;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class TickerViewModel extends ViewModel {

    private MutableLiveData<String> current_url;

    private MutableLiveData<LinkedList<String>> tickers_list;

    public MutableLiveData<String> getUrl() {
        if (current_url == null) {
            current_url = new MutableLiveData<>();
            String newURL = "https://seekingalpha.com/";
            current_url.setValue(newURL);
        }
        return current_url;
    }

    public void setUrl(String url) {
        if (current_url == null) {
            current_url = new MutableLiveData<>();
        }
        current_url.setValue("https://seekingalpha.com/symbol/" + url);
    }

    public MutableLiveData<LinkedList<String>> getTickers() {
        if (tickers_list == null) {
            tickers_list = new MutableLiveData<>();
            LinkedList<String> list = new LinkedList<>();
            list.add("BAC");
            list.add("AAPL");
            list.add("DIS");
            tickers_list.setValue(list);
        }
        return tickers_list;
    }

    public void setTickers(LinkedList<String> list) {
        if (tickers_list == null) {
            tickers_list = new MutableLiveData<>();
        }
        tickers_list.setValue(list);
    }

    public void addTickers(String ticker) {
        LinkedList<String> list = tickers_list.getValue();
        if (list.size() >= 6) {
            list.removeLast();
        }
        list.add(ticker);
        tickers_list.setValue(list);
    }

}
