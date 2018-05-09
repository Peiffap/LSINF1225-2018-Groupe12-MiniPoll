package be.lsinf1225gr12.minipoll.minipollapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FriendMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_menu);
    }
    public void addFriend(View v){

    }
    public void friendList(View v){
        Intent intent = new Intent( this, Friend_List_Activity.class);
        startActivity(intent);
    }
}
