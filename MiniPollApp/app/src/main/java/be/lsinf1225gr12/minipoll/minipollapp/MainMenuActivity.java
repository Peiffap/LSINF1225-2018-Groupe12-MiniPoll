package be.lsinf1225gr12.minipoll.minipollapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Gere la navigation a travers les fonctionnalites de l'application
 *
 * @author Augustin Delecluse
 * @version 1
 */

public class MainMenuActivity extends Activity {
//Tous les noms d'activites sont a changer
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void create(View v){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void choice(View v){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void profile(View v){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void MCQ(View v){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void friends(View v){
        Intent intent = new Intent(this, FriendsManagementActivity.class);
        startActivity(intent);
    }

    public void poll(View v){
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    /**
     * configure le bouton de retour pour qu'il fasse quitter l'application
     */
    @Override
    public void onBackPressed() {
        finish();
    }
}
