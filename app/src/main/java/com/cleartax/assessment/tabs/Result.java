package com.cleartax.assessment.tabs;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cleartax.assessment.MainActivity;
import com.cleartax.assessment.R;
import com.cleartax.assessment.model.Frequency;
import com.cleartax.assessment.utils.InternetConnection;

import java.util.Arrays;

/**
 * Created by alenave on 22/05/16.
 */
public class Result extends Fragment implements View.OnClickListener {
    private View view;
    private Button checkFrequency;
    private TextView result;
    private Button retryButton;
    public SharedPreferences mUserSharedPreferences;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static String  searchTerm = "ClearTax";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!InternetConnection.isInternetConnected(getContext())) {
            checkFrequency.setVisibility(View.GONE);
            result.setVisibility(View.GONE);
            retryButton.setVisibility(View.VISIBLE);
        }
    }

    private void initViews(){
        checkFrequency = (Button) view.findViewById(R.id.check_frequency);
        result = (TextView) view.findViewById(R.id.result);
        retryButton = (Button) view.findViewById(R.id.retry);
        checkFrequency.setOnClickListener(this);
        retryButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_frequency:
                calculateFrequency();
                break;
            case R.id.retry:
                onClickRetry();
                break;
        }
    }

    private void onClickRetry() {
        if(InternetConnection.isInternetConnected(getContext())) {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        }
    }

    private void calculateFrequency() {
        Frequency[] frequencies = new Frequency[3];
        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, getContext().MODE_PRIVATE);
        StringBuilder resultText = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            frequencies[i] = new Frequency();

            frequencies[i].setWord(prefs.getString(i + "_word", ""));
            frequencies[i].setFrequency(prefs.getInt(i + "_frequency", 0));
        }
        Arrays.sort(frequencies);
        for(Frequency frequency: frequencies){
            resultText.append(frequency.getWord() + " : " + frequency.getFrequency() + "\n");
        }
        result.setText(resultText);
    }

//    private void showResult() {
//
//    }
}
