package be.uclouvain.lsinf1225.musicplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import be.uclouvain.lsinf1225.musicplayer.MusicPlayerApp;
import be.uclouvain.lsinf1225.musicplayer.R;
import be.uclouvain.lsinf1225.musicplayer.model.User;

/**
 * Gère l'affichage du menu principal de l'application.
 *
 * @author Damien Mercier
 * @version 1
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Affichage du message de bienvenue.
        TextView welcomeTxt = findViewById(R.id.welcomeTxt);
        welcomeTxt.setText(getString(R.string.main_welcome) + " " + User.getConnectedUser().getName());

    }


    /*
     * @note Les méthodes show, search, add et logout sont appelées lors d'un clic sur les boutons
     * correspondant grâce à l'attribut onClick présent dans les fichiers de layout.
     *
     * Lire http://developer.android.com/reference/android/R.attr.html#onClick
     */

    /**
     * Lance l'activité d'affichage de la liste des éléments.
     */
    public void show(View v) {
        Intent intent = new Intent(this, ShowListActivity.class);
        startActivity(intent);
    }

    /**
     * Lance l'activité de recherche d'un élément.
     */
    public void search(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    /**
     * Déconnecte l'utilisateur actuellement connecté et retourne vers l'écran de connexion.
     */
    public void logout(View v) {
        User.logout();
        finish();
    }

    /**
     * Désactive le bouton de retour. Désactive le retour à l'activité précédente (donc l'écran de
     * connexion dans ce cas-ci) et affiche un message indiquant qu'il faut se déconnecter.
     */
    @Override
    public void onBackPressed() {
        // On désactive le retour (car on se trouve au menu principal) en ne faisant
        // rien dans cette méthode si ce n'est afficher un message à l'utilisateur.
        MusicPlayerApp.notifyShort(R.string.main_back_button_disable);
    }

    public void player(View view) {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }
}
