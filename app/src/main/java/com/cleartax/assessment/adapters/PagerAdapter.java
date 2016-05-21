package com.cleartax.assessment.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cleartax.assessment.tabs.ClearTax;
import com.cleartax.assessment.tabs.Result;
import com.cleartax.assessment.tabs.search.tweets.SearchTweets;

/**
 * Created by alenave on 03/11/15.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }




    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ClearTax clearTax = new ClearTax();
                return clearTax;
            case 1:
                SearchTweets searchTweets = new SearchTweets();
                return searchTweets;
            case 2:
                Result result = new Result();
                return result;
            default:
                return null;
        }
    }



    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}