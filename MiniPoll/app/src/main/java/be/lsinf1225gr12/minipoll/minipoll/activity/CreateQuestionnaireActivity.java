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
        boolean fuck=false;
        user = new ArrayList<>();
        int id[] = getIntent().getIntArrayExtra(MySQLiteHelper.getKeyUserId());

        if (id==null)
            MiniPollApp.notifyShort(R.string.list_error);

        for (int i=0;i<id.length;++i)
            user.add(User.getUserWithId(id[i]));

        setContentView(R.layout.activity_create_questionnaire);
        TextView textView = findViewById(R.id.textView5);

        textView.setText(String.valueOf(user.size()));
        if (fuck)
            MiniPollApp.notifyShort(R.string.list_error);

    }
}