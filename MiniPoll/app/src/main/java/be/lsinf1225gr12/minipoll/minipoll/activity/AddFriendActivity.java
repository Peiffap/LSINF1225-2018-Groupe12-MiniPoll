package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.adapter.AddFriendAdapter;

public class AddFriendActivity extends FragmentActivity {

    ViewPager VP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        VP = findViewById(R.id.SwipeAdd);
        AddFriendAdapter AFA= new AddFriendAdapter(getSupportFragmentManager());
        VP.setAdapter(AFA);
    }
}
