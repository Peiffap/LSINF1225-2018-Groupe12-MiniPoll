package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.adapter.ChooseFriendAdapter;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class ChooseFriendActivity extends AppCompatActivity
{

    public static final String FriendMemory = "be.lsinf1225gr12.minipoll.minipoll.activity.FriendMemory";

    TextView noF;
    ListView LVC;
    Button BC;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_friend);
        ListView LV = findViewById(R.id.LVC);
        if (User.getFriends(User.getConnectedUser()).size()==0){
            LVC.setVisibility(View.GONE);
            BC = (Button) findViewById(R.id.BC);
            BC.setVisibility(View.GONE);
            noF = (TextView) findViewById(R.id.noF);
            noF.setText("Vous n'avez pas d'amis, vous devriez vous en inquiéter");
        }
        String f = "";
        for(int i=0; i<User.getFriends(User.getConnectedUser()).size();i++){
            f+=(User.getFriends(User.getConnectedUser()).get(i).getLogin()+"-");
        }
        String[] friendList= f.split("-");
        ChooseFriendAdapter adapter = new ChooseFriendAdapter(this, friendList);
        LV.setAdapter(adapter);
    }
    public void next (View v){
        Intent pIntent = getIntent();
        int Type = pIntent.getIntExtra(CreateActivity.TypeMemory,0);
        String[] Chosen = {"PlaceHolder1","PlaceHolder2"}; //put chosen friends
        Intent intent;
        if(Type==0){
            intent = new Intent(this,CreateSondageActivity.class);
        }
        else{
            intent = new Intent(this,CreateQuestionnaireActivity.class);
        }
        intent.putExtra(FriendMemory,Chosen);
        startActivity(intent);
    }
}
