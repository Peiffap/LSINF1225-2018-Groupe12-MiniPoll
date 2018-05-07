package be.lsinf1225gr12.minipoll.minipollapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

/**
 * Lists all known friends.
 *
 * @author Augustin Delecluse
 * @version 1
 */

public class FriendsListActivity extends FragmentActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

    }
    public void onTouch(MotionEvent touch){
        float x1=0, y1, x2, y2;
        switch(touch.getAction()){
            case MotionEvent.ACTION_DOWN: x1= touch.getX(); y1=touch.getY(); break;
            case MotionEvent.ACTION_UP:  x2= touch.getX(); y2=touch.getY();
            if(x1<x2) {

            }break;
        }

    }

    public void listFriend(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addFriend(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
