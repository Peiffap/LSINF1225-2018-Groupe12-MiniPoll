package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.adapter.MyListViewAdapter;
import be.lsinf1225gr12.minipoll.minipoll.model.User;
import be.lsinf1225gr12.minipoll.minipoll.R;

/**
 * Gere la navigation a travers les fonctionnalites de l'application
 *
 * @author Augustin Delecluse
 * @version 1
 */

public class MainActivity extends Activity {
    // Tous les noms d'activites sont a changer

    private static long timeBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        timeBackPressed=System.currentTimeMillis()-2000; //eviter de quitter dès qu'on appuie sur retour
        setContentView(R.layout.activity_main);
    }

    public void create(View v){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void choice(View v){
        Intent intent = new Intent(this, ShowListActivity.class);
        startActivity(intent);
    }

    public void profile(View v){
        Intent intent = new Intent(this, UpdateProfileActivity.class);
        startActivity(intent);
    }

    public void MCQ(View v){
        Intent intent = new Intent(this, ChooseFriendActivity.class);
        startActivity(intent);
    }

    public void friends(View v){
        Intent intent = new Intent(this, FriendMenuActivity.class);
        startActivity(intent);
    }

    public void poll(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * configure le bouton de retour pour qu'il fasse quitter l'application
     */

    @Override
    public void onBackPressed() {
        if (timeBackPressed + 2000 > System.currentTimeMillis()) //déjà appuyé sur back il y a moins de 2 secondes
        {
            finish();
            User.logout();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        } else {
            MiniPollApp.notifyShort(R.string.menu_logout);
        }
        timeBackPressed= System.currentTimeMillis();

    }

}
