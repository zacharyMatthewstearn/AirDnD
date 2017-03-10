package com.epicodus.airdd.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.ui.GameDetailFragment;

import java.util.List;

public class GamePagerAdapter extends FragmentPagerAdapter {

    private List<Game> mGames;

    public GamePagerAdapter(FragmentManager _fragmentManager, List<Game> _games) {
        super(_fragmentManager);
        mGames = _games;
    }

    @Override
    public Fragment getItem(int _position) {
        return GameDetailFragment.newInstance(mGames, _position);
    }

    @Override
    public int getCount() {
        return mGames.size();
    }

    @Override
    public CharSequence getPageTitle(int _position) {
        return mGames.get(_position).getTitle();
    }
}
