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
import be.lsinf1225gr12.minipoll.minipoll.model.MCQ;
import be.lsinf1225gr12.minipoll.minipoll.model.Question;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class CreateQuestionActivity extends AppCompatActivity {

    private MCQ mcq;
    private ArrayList<User> user;
    private int numberChoice;
    private boolean completed[]= new boolean[5];
    private int actualNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actualNumber = getIntent().getIntExtra("InfosQuestion",1);
        setContentView(R.layout.activity_create_question);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        ArrayList<String> credentials = bundle.getStringArrayList("credentials");

        int author = Integer.parseInt(credentials.get(0));
        long date = Long.parseLong(credentials.get(1));
        actualNumber = Integer.parseInt(credentials.get(2));
       // mcq = getMCQ(author,date);
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
     * enregistre une question dans la DB et dans la liste de Question
     */
    private void registerQuestion()
    {
        EditText loginEditText[] = new EditText[5];
        loginEditText[0] = findViewById(R.id.editText6);
        loginEditText[1] = findViewById(R.id.editText7);
        loginEditText[2] = findViewById(R.id.editText8);
        loginEditText[3] = findViewById(R.id.editText9);
        EditText editText = findViewById(R.id.editText5);
        Question quest = mcq.addQuestion(editText.getText().toString(),actualNumber); //ajoute la question à la DB
        for (int i=0;i<4;++i)
            mcq.addQuestionChoice(quest,loginEditText[i].getText().toString(),i); //ajoute les réponses à la DB
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
            registerQuestion();
            if (actualNumber==1)
            {
                registerQuestion();
                Intent intent = new Intent(this, MainActivity.class);
                //intent.putExtra("ArrayList<Question>", questions); //passe le tableau de Questions en arguments
                startActivity(intent);
            }
            for (int i=0;i<5;++i) {
                loginEditText[i].setText("");
            }
            resetQuestion();
            actualNumber--;
        }
    }
}