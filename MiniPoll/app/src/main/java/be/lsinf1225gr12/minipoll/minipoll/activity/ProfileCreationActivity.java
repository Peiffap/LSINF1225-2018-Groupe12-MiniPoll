package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

import static be.lsinf1225gr12.minipoll.minipoll.InputValidation.isNullOrWhitespace;
import static be.lsinf1225gr12.minipoll.minipoll.InputValidation.isValidField;
import static be.lsinf1225gr12.minipoll.minipoll.InputValidation.isValidEmail;

public class ProfileCreationActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);
    }

    /**
     * Vérifie que les champs sont cohérents et crée un nouveau profil.
     * <p>
     * Cette méthode permet à l'utilisateur de créer son profil. Si les champs sont cohérents, elle
     * affiche l'écran de menu, sinon un message est affiché à l'utilisateur.
     * <p>
     * Cette méthode est appelée grâce à l'attribut onClick indiqué dans le fichier xml de layout
     * sur le bouton de création de compte. Elle peut également être appelée depuis la méthode
     * "onEditorAction" de cette classe.
     *
     * @param v Une vue quelconque (n'est pas utilisé ici, mais requis par le onClick)
     */
    public void createProfile(View v) {
        EditText emailEditText = findViewById(R.id.profile_creation_email);
        String email = emailEditText.getText().toString();
        EditText firstNameEditText = findViewById(R.id.profile_creation_first_name);
        String firstName = firstNameEditText.getText().toString();
        EditText lastNameEditText = findViewById(R.id.profile_creation_last_name);
        String lastName = lastNameEditText.getText().toString();
        EditText picEditText = findViewById(R.id.profile_creation_pic);
        String pic = picEditText.getText().toString();

        if (!isValidField(pic)) {
            pic = null;
        }
        if (isValidEmail(email)) {
            if (isValidField(firstName)) {
                if (isValidField(lastName)) {
                    //Get the bundle
                    Bundle bundle = getIntent().getExtras();

                    //Extract the data…
                    ArrayList<String> credentials = bundle.getStringArrayList("credentials");

                    String login = credentials.get(0);
                    String password = credentials.get(1);

                    // User.createNewUser(login, password, pic, email, firstName, lastName, 0);

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    MiniPollApp.notifyShort(R.string.profile_creation_invalid_last_name_msg);
                }
            } else {
                MiniPollApp.notifyShort(R.string.profile_creation_invalid_first_name_msg);
            }
        } else {
            MiniPollApp.notifyShort(R.string.profile_creation_invalid_email_msg);
        }
    }

    /**
     * Récupère les actions faites depuis le clavier.
     * <p>
     * Récupère les actions faites depuis le clavier lors de l'édition des champs de texte afin
     * de permettre de créer un profil depuis le bouton "Terminer" du clavier. (Cela évite à
     * l'utilisateur de devoir fermer le clavier et de cliquer sur le bouton créer mon profil).
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // L'attribut android:imeOptions="actionNext" est défini dans le fichier xml de layout
        // (activity_login.xml), L'actionId attendue est donc IME_ACTION_NEXT.
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            createProfile(v);
            return true;
        }
        return false;
    }
}