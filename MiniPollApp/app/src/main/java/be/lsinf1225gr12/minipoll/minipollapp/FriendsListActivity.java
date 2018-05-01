package be.lsinf1225gr12.minipoll.minipollapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Gere l'affichage de la liste d'amis connus
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
        viewPager=(ViewPager)findViewById(R.id.view_paper);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);

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
