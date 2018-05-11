package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.adapter.ChooseFriendChoiceAdapter;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class ChooseFriendChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_friend_choice);
        ListView LV = (ListView) findViewById(R.id.ChoiceListView);
        LV.setAdapter(new ChooseFriendChoiceAdapter(this,User.getFriends(User.getConnectedUser())));
    }
}
