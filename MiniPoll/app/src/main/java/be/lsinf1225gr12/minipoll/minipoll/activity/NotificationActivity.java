package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.adapter.NotificationAdapter;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class NotificationActivity extends AppCompatActivity {

    TextView noN;
    ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        LV = (ListView) findViewById(R.id.LVN);
        if(User.getDemands(User.getConnectedUser()).size()==0){
            noN=(TextView) findViewById(R.id.noN);
            noN.setText("Vous n'avez pas de demande en attente");
            LV.setVisibility(View.GONE);
        }
        String f = "";
        for(int i=0; i<User.getDemands(User.getConnectedUser()).size();i++){
            f+=(User.getDemands(User.getConnectedUser()).get(i).getLogin()+"-");
        }
        String[] friendList= f.split("-");
        NotificationAdapter adapter = new NotificationAdapter(this, friendList);
        LV.setAdapter(adapter);
    }
}
