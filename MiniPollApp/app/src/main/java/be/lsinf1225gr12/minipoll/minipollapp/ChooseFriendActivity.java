package be.lsinf1225gr12.minipoll.minipollapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChooseFriendActivity extends AppCompatActivity
{

    private static final String TAG = "ChooseFriendActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_friend);
        Log.d(TAG, "onCreate: Started.");
        ListView mListView = findViewById(R.id.listView);

        ArrayList<User> friendList= new ArrayList<>();
        // Set an ArrayList with all friends.

        FriendListAdapter adapter = new FriendListAdapter (this, R.layout.adapter_view_layout, friendList);
        mListView.setAdapter(adapter);
    }
}
