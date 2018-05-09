package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

    public class CreateActivity extends AppCompatActivity
    {

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create);
        }
    public void createChoix(View v)
    {
        Intent intent = new Intent( this, CreateChoixActivity.class);
        startActivity(intent);
    }
    public void createSondage(View v)
    {
        Intent intent = new Intent(this, CreateSondageActivity.class);
        startActivity(intent);
    }
    public void createQuestionnaire(View v)
    {
        Intent intent = new Intent(this, CreateQuestionnaireActivity.class);
        startActivity(intent);
    }
}
