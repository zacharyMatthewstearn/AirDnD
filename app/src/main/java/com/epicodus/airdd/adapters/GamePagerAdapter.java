package com.epicodus.airdd.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.ui.GameDetailFragment;

import java.util.List;

public class GamePagerAdapter extends FragmentPagerAdapter {
    private List<Game> mGames;

    public GamePagerAdapter(FragmentManager fm, List<Game> games) {
        super(fm);
        mGames = games;
    }

    @Override
    public Fragment getItem(int position) {
        return GameDetailFragment.newInstance(mGames, position);
    }

    @Override
    public int getCount() {
        return mGames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mGames.get(position).getTitle();
    }
}
