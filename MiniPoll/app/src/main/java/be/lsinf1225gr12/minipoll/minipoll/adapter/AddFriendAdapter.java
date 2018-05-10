package be.lsinf1225gr12.minipoll.minipoll.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class AddFriendAdapter extends FragmentStatePagerAdapter{

    public AddFriendAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frg = new AddFriendFragment();
        Bundle bdl = new Bundle();
        bdl.putInt("count",position+1);
        frg.setArguments(bdl);
        return frg;
    }

    @Override
    public int getCount() {
        return Math.max(1,AddFriendFragment.getNonFriend().size());
    }
}
