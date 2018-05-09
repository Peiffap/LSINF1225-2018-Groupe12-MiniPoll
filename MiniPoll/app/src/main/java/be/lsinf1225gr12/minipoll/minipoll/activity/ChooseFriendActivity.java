package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.adapter.ChooseFriendAdapter;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class ChooseFriendActivity extends AppCompatActivity
{

    public static final String TypeMemory = "be.lsinf1225gr12.minipoll.minipoll.activity.TypeMemory";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_friend);
        ListView mListView = findViewById(R.id.listView);
        String f = "";
        for(int i=0; i<User.getFriends(User.getConnectedUser()).size();i++){
            f+=(User.getFriends(User.getConnectedUser()).get(i).getLogin()+"-");
        }
        String[] friendList= f.split("-");
        ChooseFriendAdapter adapter = new ChooseFriendAdapter(this, friendList);
        mListView.setAdapter(adapter);
    }
    public void next (View v){
        Intent pIntent = getIntent();
        int Type = pIntent.getIntExtra(CreateActivity.TypeMemory,0);
        Intent intent;
        /*if(Integer.parseInt(TypeMemory)==0){
            intent = new Intent(this,CreatePollActivity.class);
        }
        else{
            intent = new Intent(this,CreateMCQ.class);
        }
        */
    }
}
