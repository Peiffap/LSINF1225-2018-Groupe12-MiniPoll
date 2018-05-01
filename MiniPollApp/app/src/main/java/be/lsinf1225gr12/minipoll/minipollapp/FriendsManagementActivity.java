package be.lsinf1225gr12.minipoll.minipollapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Gere le menu des amis (liste d'amis et ajout d'amis)
 *
 * @author Augustin Delecluse
 * @version 1
 */

public class FriendsManagementActivity extends Activity {
//Tous les noms d'activites sont a changer
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_management);
    }

    public void listFriend(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addFriend(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
