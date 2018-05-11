package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class CreateQuestionnaireActivity extends AppCompatActivity {

    private ArrayList<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = new ArrayList<>();
        int id = getIntent().getIntExtra(MySQLiteHelper.getKeyUserId(), -1);
        user.add(User.getUserWithId(id));
        /*
        while (id!=-1) //rajoute les utilisateurs sélectionnés
        {
            user.add(User.getUserWithId(id));
            id = getIntent().getIntExtra(MySQLiteHelper.getKeyUserId(), -1);
        }
        */
        setContentView(R.layout.activity_create_questionnaire);
        //TextView textView = findViewById(R.id.textView5);
        //textView.setText("ok");

    }
}