package com.cleartax.assessment.tabs.search.tweets;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cleartax.assessment.R;
import com.cleartax.assessment.adapters.MyRecyclerAdapter;
import com.cleartax.assessment.model.Tweet;
import com.cleartax.assessment.utils.JsonParser;
import com.cleartax.assessment.utils.ProgressDlg;
import com.cleartax.assessment.utils.trie.MyTrie;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alenave on 21/05/16.
 */
public class SearchTweets extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    private MyRecyclerAdapter adapter;
    private EditText searchTerm;
    private Button searcButton;
    private String searchTermString = "ClearTax";
    public List<Tweet> tweetsList = new ArrayList<Tweet>();
    public static boolean isTweetFetched = false;
    public SharedPreferences mUserSharedPreferences;
    public static final String MY_PREFS_NAME = "MyPrefsFile";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_tweets, container, false);
        initViews();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new MyRecyclerAdapter(getActivity(), tweetsList );
        recyclerView.setAdapter(adapter);
        fetchNews(searchTermString);
    }

    private void initViews() {
        searchTerm = (EditText) view.findViewById(R.id.search_term);
        searchTerm.clearFocus();
        searcButton = (Button) view.findViewById(R.id.search_button);
        searcButton.setOnClickListener(this);
    }



    public void fetchNews(final String searchTermString) {

        isTweetFetched = true;
        new AsyncTask<String, Void, JSONObject>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ProgressDlg.showProgressDialog(getContext(), "fetching results for " + searchTermString);
            }

            @Override
            protected JSONObject doInBackground(String... params) {
                try {
                    JsonParser jsonParser = new JsonParser();
                    JSONObject tweetObject = jsonParser.parsing(searchTermString);
                    return tweetObject;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(JSONObject feedObject) {
                super.onPostExecute(feedObject);
                try {
                    tweetsList.clear();
                    JsonParser jsonParser = new JsonParser();
                    tweetsList.addAll(jsonParser.tweetFeedList(feedObject));
                    setFrequncyResult(tweetsList);
                    adapter.notifyDataSetChanged();
                    searchTerm.clearFocus();
                    ProgressDlg.hideProgressDialog();
                    searchTerm.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute(null, null, null);
    }


    private void setFrequncyResult(List<Tweet> tweetsList) {
        MyTrie t = new MyTrie(3);
        for (Tweet tweet : tweetsList) {
            String[] array=tweet.getText().split(" ");
            for (int i = 0; i < array.length; i++) {
                t.insert(array[i]);
            }
        }
        t.display(getContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                userSearchTerm();
                break;
        }
    }

    private void userSearchTerm(){
        searchTermString = searchTerm.getText().toString();
        fetchNews(searchTermString);
    }
}
