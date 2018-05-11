package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class CreateSondageActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String NumberChoice = "be.lsinf1225gr12.minipoll.minipoll.activity.NumberChoice";
    public static final String FriendMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.FriendMemoryPoll";
    public static final String FormatMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.FormatMemoryPoll";
    public static final String NameMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.NameMemoryPoll";
    public static final String TopMemoryPoll = "be.lsinf1225gr12.minipoll.minipoll.activity.TopMemoryPoll";
    String text="";
    EditText ET;
    EditText ET2;
    Spinner S;
    EditText Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sondage);
        S= findViewById(R.id.formatSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.format,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        S.setAdapter(adapter);
        S.setOnItemSelectedListener(this);
    }

    public void next(View v){

        ET = (EditText) findViewById(R.id.giveNumber);
        Name = (EditText) findViewById(R.id.PollName);
        ET2 = (EditText) findViewById(R.id.giveNumber2);
        String et = ET.getText().toString();
        String et2 = ET2.getText().toString();
        String name = Name.getText().toString();
        Log.d("Crash?",name);
        if (et.equals("")||(et2.equals(""))){
            Toast.makeText(this,"Veuillez entrer un nombre",Toast.LENGTH_SHORT);
        }
        else if(Integer.parseInt(et)<2||Integer.parseInt(et)>6){
            Toast.makeText(this,"Veuillez entrer un nombre entre 2 et 6 pour le nombre de choix",Toast.LENGTH_SHORT);
        }
        else if(Integer.parseInt(et2)<1||Integer.parseInt(et2)>6){
            Toast.makeText(this,"Veuillez entrer un nombre entre 1 et 6 pour le nombre de top",Toast.LENGTH_SHORT);
        }
        else if (name.equals("")){
            Toast.makeText(this,"Veuillez entrer un nom pour votre sondage",Toast.LENGTH_SHORT);
        }
        else {
            Intent pIntent = getIntent();
            int i = Integer.parseInt(et);
            int j = Integer.parseInt(et2);
            Intent intent = new Intent(this, PollEditActivity.class);
            intent.putExtra(NumberChoice,i);
            intent.putExtra(FriendMemoryPoll,pIntent.getStringArrayExtra(ChooseFriendActivity.FriendMemory));
            intent.putExtra(FormatMemoryPoll,text);
            intent.putExtra(NameMemoryPoll,name);
            intent.putExtra(TopMemoryPoll,j);
            startActivity(intent);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}