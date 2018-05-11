package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class CreateQuestionActivity extends AppCompatActivity {

    private ArrayList<User> user;
    private int numberChoice;
    private boolean completed[]= new boolean[5];
    private int actualNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //actualNumber = getIntent().getIntExtra("InfosQuestion",1);
        //setContentView(R.layout.activity_create_question);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void resetQuestion()
    {
        EditText loginEditText[] = new EditText[5];
        loginEditText[0] = findViewById(R.id.editText6);
        loginEditText[1] = findViewById(R.id.editText7);
        loginEditText[2] = findViewById(R.id.editText8);
        loginEditText[3] = findViewById(R.id.editText9);
        loginEditText[4] = findViewById(R.id.editText5);
        for (int i=0;i<5;++i)
        {
            loginEditText[i].setText("");
        }
    }

    /**
     * TODO
     * enregistre une question dans la DB
     */
    private void registerQuestion()
    {

    }

    public void anotherQuestion(View v)
    {
        boolean canPass=true;
        EditText loginEditText[] = new EditText[5];
        loginEditText[0] = findViewById(R.id.editText6);
        loginEditText[1] = findViewById(R.id.editText7);
        loginEditText[2] = findViewById(R.id.editText8);
        loginEditText[3] = findViewById(R.id.editText9);
        loginEditText[4] = findViewById(R.id.editText5);
        for (int i=0;i<5 && canPass;++i)
        {
            if (loginEditText[i]==null || loginEditText[i].getText().toString().equals(""))
            {
                canPass=false;
                MiniPollApp.notifyShort(R.string.manque_champs);
            }
        }
        if (canPass)
        {
            actualNumber--;
            if (actualNumber==0)
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            for (int i=0;i<5;++i)
            {
                registerQuestion();
                resetQuestion();
                loginEditText[i].setText("");
            }
        }
    }
}