package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Date;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.Poll;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class PollEditActivity extends AppCompatActivity {

    public static final String NumberChoice = "be.lsinf1225gr12.minipoll.minipoll.activity.NumberChoice";
    public static final String FriendMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.FriendMemoryPoll";
    public static final String FormatMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.FormatMemoryPoll";
    public static final String NameMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.NameMemoryPoll";
    public static final String QuestionMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.QuestionMemoryPoll";
    public static final String ChoiceMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.ChoiceMemoryPoll";
    EditText Title;
    EditText Choice;
    EditText ChoiceToErase;

    ImageView Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_edit);
        Intent pintent = getIntent();
        int c = pintent.getIntExtra(CreateSondageActivity.NumberChoice,2);
        for(int i=c;i<6;i++){
            switch(i){
                case 5 :
                    Back= (ImageView) findViewById(R.id.toHide4);
                    Back.setVisibility(View.GONE);
                    ChoiceToErase= (EditText) findViewById(R.id.hideET4);
                    ChoiceToErase.setVisibility(View.GONE);
                    break;
                case 4 :
                    Back= (ImageView) findViewById(R.id.toHide3);
                    Back.setVisibility(View.GONE);
                    ChoiceToErase= (EditText) findViewById(R.id.hideET3);
                    ChoiceToErase.setVisibility(View.GONE);
                    break;
                case 3 :
                    Back= (ImageView) findViewById(R.id.toHide2);
                    Back.setVisibility(View.GONE);
                    ChoiceToErase= (EditText) findViewById(R.id.hideET2);
                    ChoiceToErase.setVisibility(View.GONE);
                    break;
                case 2 :
                    Back= (ImageView) findViewById(R.id.toHide1);
                    Back.setVisibility(View.GONE);
                    ChoiceToErase= (EditText) findViewById(R.id.hideET1);
                    ChoiceToErase.setVisibility(View.GONE);
                    break;
            }
        }
    }
    public void create (View v){
        Intent pintent = getIntent();
        int c = pintent.getIntExtra(CreateSondageActivity.NumberChoice,2);
        boolean found = false;
        ChoiceToErase = (EditText) findViewById(R.id.hideET4);
        found=(ChoiceToErase.getText().toString().equals("Choix 6")&&c>5)? true:found;
        ChoiceToErase = (EditText) findViewById(R.id.hideET3);
        found=(ChoiceToErase.getText().toString().equals("Choix 5")&&c>4)? true:found;
        ChoiceToErase = (EditText) findViewById(R.id.hideET2);
        found=(ChoiceToErase.getText().toString().equals("Choix 4")&&c>3)? true:found;
        ChoiceToErase = (EditText) findViewById(R.id.hideET1);
        found=(ChoiceToErase.getText().toString().equals("Choix 3")&&c>2)? true:found;
        ChoiceToErase = (EditText) findViewById(R.id.editText3);
        found=(ChoiceToErase.getText().toString().equals("Choix 2"))? true:found;
        ChoiceToErase = (EditText) findViewById(R.id.editText5);
        found=(ChoiceToErase.getText().toString().equals("Choix 1"))? true:found;
        ChoiceToErase = (EditText) findViewById(R.id.ETQuestion);
        found=(ChoiceToErase.getText().toString().equals(""))? true:found;
        if(found){
            MiniPollApp.notifyShort(R.string.invalidfield);
        }
        else{
            Title = (EditText) findViewById(R.id.ETQuestion);
            Log.d("crashtest","merde1");
            Poll P = new Poll(pintent.getIntExtra(CreateSondageActivity.TopMemoryPoll,1),c,System.currentTimeMillis(), User.getConnectedUser(),pintent.getStringExtra(NameMemoryPoll),false,"Text",Title.getText().toString());
            Log.d("crashtest","merde2");
            Poll.addPoll(P);
            Log.d("crashtest","merde3");
            Choice=(EditText) findViewById(R.id.editText3);
            Log.d("crashtest","merde4");
            P.addChoicePoll(Choice.getText().toString(),1);
            Choice=(EditText) findViewById(R.id.editText5);
            P.addChoicePoll(Choice.getText().toString(),2);
            Log.d("crashtest","merde5");
            if(c>2){
                Choice=(EditText) findViewById(R.id.hideET1);
                P.addChoicePoll(Choice.getText().toString(),3);
            }
            if(c>3){
                Choice=(EditText) findViewById(R.id.hideET2);
                P.addChoicePoll(Choice.getText().toString(),4);
            }
            if(c>4){
                Choice=(EditText) findViewById(R.id.hideET3);
                P.addChoicePoll(Choice.getText().toString(),5);
            }
            if(c>5){
                Choice=(EditText) findViewById(R.id.hideET4);
                P.addChoicePoll(Choice.getText().toString(),6);
            }
            Log.d("crashtest","merde6");


        }
    }

}
