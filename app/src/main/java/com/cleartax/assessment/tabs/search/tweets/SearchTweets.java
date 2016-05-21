package com.cleartax.assessment.tabs.search.tweets;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cleartax.assessment.R;
import com.cleartax.assessment.adapters.MyRecyclerAdapter;
import com.cleartax.assessment.model.Tweet;
import com.cleartax.assessment.utils.JsonParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alenave on 21/05/16.
 */
public class SearchTweets extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    private MyRecyclerAdapter adapter;
    private String searchTerm = "ClearTax";
    public List<Tweet> tweetsList = new ArrayList<Tweet>();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_tweets, container, false);
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
        fetchNews();
    }

    private void fetchNews() {


        new AsyncTask<Void, Void, JSONObject>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected JSONObject doInBackground(Void... params) {
                try {
                    JsonParser jsonParser = new JsonParser();
                    JSONObject tweetObject = jsonParser.parsing(searchTerm);
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
                    JsonParser jsonParser = new JsonParser();
                        tweetsList.addAll(jsonParser.tweetFeedList(feedObject));
                        adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute(null, null, null);

    }
}
