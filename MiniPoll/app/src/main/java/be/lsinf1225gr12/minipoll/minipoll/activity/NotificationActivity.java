package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class NotificationActivity extends AppCompatActivity {

    TextView noN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        if(User.getDemands(User.getConnectedUser()).size()==0){
            noN=(TextView) findViewById(R.id.noN);
            noN.setText("Vous n'avez pas de demande en attente");
        }
    }
}
