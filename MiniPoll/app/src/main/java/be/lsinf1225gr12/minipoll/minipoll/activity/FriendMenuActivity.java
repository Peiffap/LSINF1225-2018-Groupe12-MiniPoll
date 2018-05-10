package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import be.lsinf1225gr12.minipoll.minipoll.R;

public class FriendMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_menu);
    }
    public void addFriend(View v){
        Intent intent = new Intent( this, AddFriendActivity.class);
        startActivity(intent);
    }
    public void friendList(View v){
        Intent intent = new Intent( this, Friend_List_Activity.class);
        startActivity(intent);
    }

    public void Notifications(View v){
        Intent intent = new Intent( this, NotificationActivity.class);
        startActivity(intent);
    }
}
