package com.cleartax.assessment.tabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.cleartax.assessment.R;

/**
 * Created by alenave on 21/05/16.
 */
public class ClearTax extends Fragment {

    View view;
    WebView webView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cleartax, container, false);
        webView = (WebView) view.findViewById(R.id.webview);
        webView.loadUrl("https://cleartax.in/");
        return view;
    }
}
