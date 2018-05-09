package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.activity.adapter.SwiperAdapter;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class Friend_List_Activity extends FragmentActivity {
    ViewPager VP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend__list_);
        VP = findViewById(R.id.swipe_friend);
        SwiperAdapter SA = new SwiperAdapter(getSupportFragmentManager());
        VP.setAdapter(SA);

    }
}
