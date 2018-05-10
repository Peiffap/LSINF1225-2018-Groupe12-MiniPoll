package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    String text="";
    EditText ET;
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
        String et = ET.getText().toString();
        String name = Name.getText().toString();
        if (et.equals("")){
            Toast.makeText(getBaseContext(),"Veuillez entrer un nombre",Toast.LENGTH_SHORT);
        }
        else if(Integer.parseInt(et)<2||Integer.parseInt(et)>6){
            Toast.makeText(getBaseContext(),"Veuillez entrer un nombre entre 2 et 6",Toast.LENGTH_SHORT);
        }
        else if (name.equals("")){
            Toast.makeText(getBaseContext(),"Veuillez entrer un nom pour votre sondage",Toast.LENGTH_SHORT);
        }
        else {
            Intent pIntent = getIntent();
            int i = Integer.parseInt(et);
            Intent intent = new Intent(this, PollEditActivity.class);
            intent.putExtra(NumberChoice,i);
            intent.putExtra(FriendMemoryPoll,pIntent.getStringArrayExtra(ChooseFriendActivity.FriendMemory));
            intent.putExtra(FormatMemoryPoll,text);
            intent.putExtra(NameMemoryPoll,name);
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