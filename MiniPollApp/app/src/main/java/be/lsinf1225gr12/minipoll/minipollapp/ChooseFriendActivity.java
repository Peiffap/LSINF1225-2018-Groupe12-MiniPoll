package be.lsinf1225gr12.minipoll.minipollapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChooseFriendActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_friend);
        ListView mListView = findViewById(R.id.listView);

<<<<<<< HEAD
        String[] friendList= {"Mario","Luigi"}; // example, to be removed
        // Set a String[] with every friend's name
=======
        String[] friendList= {"Mario","Luigi","Peach","Daisy","Bowser","Wario","Waluigi","Koopa","Goomba","Yoshi","Donkey Kong"}; // example, to be removed
        // Set a String[] with every friends' name
>>>>>>> d3a7ee8ad33d23956c4ed386927f7abfdd795b1a

        ChooseFriendAdapter adapter = new ChooseFriendAdapter (this, friendList);
        mListView.setAdapter(adapter);
    }
}
