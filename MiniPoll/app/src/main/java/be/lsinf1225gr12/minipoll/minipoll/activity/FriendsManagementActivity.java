package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

/**
 * Controls the friends list.
 *
 * @author Augustin Delecluse
 * @version 1
 */

public class FriendsManagementActivity extends Activity {
// TODO Change activity names.
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_management);
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
