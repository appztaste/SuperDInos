package com.drm.superdinos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by mmt6081 on 3/6/17.
 */

public class DinoCollectionPagerAdapter extends FragmentStatePagerAdapter {
    public DinoCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new DinoFragment();

        Bundle args = new Bundle();
        args.putInt(MainActivity.DINO_IDX_KEY, position);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return MainActivity.DINOS.length;
    }
}
