package com.cleartax.assessment.tabs;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.cleartax.assessment.MainActivity;
import com.cleartax.assessment.R;
import com.cleartax.assessment.utils.InternetConnection;

/**
 * Created by alenave on 21/05/16.
 */
public class ClearTax extends Fragment implements View.OnClickListener {

    View view;
    WebView webView;
    Button retryButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cleartax, container, false);
        initViews();
        webView.loadUrl("https://cleartax.in/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        return view;
    }

    private void initViews() {
        webView = (WebView) view.findViewById(R.id.webview);
        retryButton = (Button) view.findViewById(R.id.retry);
        retryButton.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!InternetConnection.isInternetConnected(getContext())) {
            webView.setVisibility(View.GONE);
            retryButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retry:
                onClickRetry();
                break;
        }
    }

    public void onClickRetry() {
        if (InternetConnection.isInternetConnected(getContext())) {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        }
    }

}
