package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.MySQLiteHelper;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.MCQ;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class CreateQuestionnaireActivity extends AppCompatActivity {

    private ArrayList<User> user;
    private Spinner choiceSpinner;
    private int numberChoice;
    private long dateCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateCreation=System.currentTimeMillis();
        boolean fuck=false;
        user = new ArrayList<>();
        int id[] = getIntent().getIntArrayExtra(MySQLiteHelper.getKeyUserId());

        if (id==null)
            MiniPollApp.notifyShort(R.string.list_error);

        for (int i=0;i<id.length;++i)
            user.add(User.getUserWithId(id[i]));
        setContentView(R.layout.activity_create_questionnaire);
        choiceSpinner = findViewById(R.id.spinner2);

        // Obtention de la liste des utilisateurs.
        ArrayList<Integer> choice = new ArrayList<>();
        for (int i=1;i<6;++i)
            choice.add(i);

        // Création d'un ArrayAdapter en utilisant la liste des utilisateurs et un layout pour le spinner existant dans Android.
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, choice);
        // On lie l'adapter au spinner.
        choiceSpinner.setAdapter(adapter);
        numberChoice=0;


    }

    public void confirm(View v)
    {

        numberChoice = (Integer) choiceSpinner.getSelectedItem();
        EditText title = findViewById(R.id.login_password);
        String name = title.getText().toString();
        if (name==null || name.equals(""))
        {
            MiniPollApp.notifyShort(R.string.mcq_alert);
        }
        else {

            SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

            ContentValues cv = new ContentValues();
            /**
             * Rajoute des utilisateurs dans la liste d'utilisateurs participants
             */
            for (int i=0;i<user.size(); ++i) {
                cv.put(MySQLiteHelper.getKeyParticipationquestionAuthor(), User.getConnectedUser().getId());
                cv.put(MySQLiteHelper.getKeyParticipationquestionDate(), dateCreation);
                cv.put(MySQLiteHelper.getKeyParticipationquestionUser(), user.get(i).getId());
                long result = db.insert(MySQLiteHelper.getTableParticipationquestion(), null, cv);
                if (result == -1) {
                    MiniPollApp.notifyShort(R.string.my_error);
                }
            }
            db.close();

            MCQ mcq = MCQ.createNewMCQ("Text", name, User.getConnectedUser(), dateCreation, numberChoice); //crée un MCQ dans la DB
            Bundle bundle = new Bundle();

            //Add your data to bundle
            ArrayList<String> credentials = new ArrayList<>();
            credentials.add(String.valueOf(dateCreation));
            credentials.add(String.valueOf(User.getConnectedUser().getId()));
            credentials.add(String.valueOf(numberChoice));
            bundle.putStringArrayList("credentials", credentials);

            //Add the bundle to the intent
            Intent intent = new Intent(this, CreateQuestionActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}