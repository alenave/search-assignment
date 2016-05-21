package com.cleartax.assessment.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleartax.assessment.R;
import com.cleartax.assessment.model.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alenave on 21/05/16.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {

    Context context;
    List<Tweet> feedItemList = new ArrayList<Tweet>();
    Tweet tweets;
    private final int VIEW_TYPE_CELL = 1;
    private final int VIEW_TYPE_FOOTER = 2;
    private String type = null;

    public MyRecyclerAdapter() {

    }

    public MyRecyclerAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.feedItemList = tweets;
    }

    @Override
    public MyRecyclerAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.CustomViewHolder holder, int position) {
        tweets = feedItemList.get(position);
        renderDataRow(holder);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    @Override
    public int getItemViewType(int position) {
        return ((position + 1) == feedItemList.size()) ? VIEW_TYPE_FOOTER : VIEW_TYPE_CELL;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView image;
        protected TextView screenName;
        protected TextView tweet;



        public CustomViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.thumbnail);
            this.screenName = (TextView) view.findViewById(R.id.title);
            this.tweet = (TextView) view.findViewById(R.id.description);
        }
    }


    void renderDataRow(CustomViewHolder customViewHolder) {
        Picasso.with(context).load(tweets.getProfileImageUrl())
                .error(R.drawable.logo)
                .placeholder(R.drawable.logo)
                .into(customViewHolder.image);

        customViewHolder.screenName.setText(tweets.getScreenName());
        customViewHolder.tweet.setText(tweets.getText());

        customViewHolder.screenName.setTag(customViewHolder);
        customViewHolder.tweet.setTag(customViewHolder);
        customViewHolder.image.setTag(customViewHolder);
    }


}
