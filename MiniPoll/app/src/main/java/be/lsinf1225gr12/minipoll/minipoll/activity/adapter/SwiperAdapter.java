package be.lsinf1225gr12.minipoll.minipoll.activity.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import be.lsinf1225gr12.minipoll.minipoll.activity.SwipeFragment;

public class SwiperAdapter extends FragmentStatePagerAdapter{

    public SwiperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frg = new SwipeFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("count",position+1);
        frg.setArguments(bdl);
        return frg;
    }

    @Override
    public int getCount() {
        return 5;
        // Replace 5 by the numbers of friend in the friend list
    }
}
