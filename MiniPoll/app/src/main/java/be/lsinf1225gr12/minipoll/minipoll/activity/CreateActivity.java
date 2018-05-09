package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import be.lsinf1225gr12.minipoll.minipoll.R;

public class CreateActivity extends AppCompatActivity
{
    public static final String TypeMemory = "be.lsinf1225gr12.minipoll.minipoll.activity.TypeMemory";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
    }
public void createChoix(View v)
{
    //Intent intent = new Intent( this, CreateChoixActivity.class);
    //startActivity(intent);
}
public void createSondage(View v)
{
    Intent intent = new Intent(this, ChooseFriendActivity.class);
    intent.putExtra("TypeMemory",0);
    startActivity(intent);
}
public void createQuestionnaire(View v)
{
    Intent intent = new Intent(this, ChooseFriendActivity.class);
    intent.putExtra("TypeMemory",1);
    startActivity(intent);
}
}
