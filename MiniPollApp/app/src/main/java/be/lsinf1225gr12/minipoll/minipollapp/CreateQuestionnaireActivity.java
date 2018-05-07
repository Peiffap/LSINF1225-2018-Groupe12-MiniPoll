package be.lsinf1225gr12.minipoll.minipollapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateQuestionnaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_questionnaire);
        Intent intent = new Intent(this, ChooseFriendActivity.class);
        startActivity(intent);
    }
}