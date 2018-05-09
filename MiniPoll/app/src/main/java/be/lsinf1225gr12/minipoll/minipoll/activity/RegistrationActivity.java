package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

public class RegistrationActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    /**
     * Vérifie les mots de passe et crée un nouveau compte.
     * <p>
     * Cette méthode vérifie les mots de passe saisis. Si ils correspondent, crée un nouveau compte et
     * affiche le menu principal, sinon un message est affiché à l'utilisateur.
     * <p>
     * Cette méthode est appelée grâce à l'attribut onClick indiqué dans le fichier xml de layout
     * sur le bouton de création de compte. Elle peut également être appelée depuis la méthode
     * "onEditorAction" de cette classe.
     *
     * @param v Une vue quelconque (n'est pas utilisé ici, mais requis par le onClick)
     */
    public void register(View v) {
        EditText loginEditText = findViewById(R.id.registration_login);
        String login = loginEditText.getText().toString();
        EditText passwordEditText = findViewById(R.id.registration_password);
        String password = passwordEditText.getText().toString();
        EditText passwordConfirmationEditText = findViewById(R.id.registration_password_confirmation);
        String passwordConfirmation = passwordConfirmationEditText.getText().toString();

        if (password.equals(passwordConfirmation)) {
            if (password.length() > 2) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                MiniPollApp.notifyShort(R.string.registration_password_too_short_msg);
            }
        } else {
            MiniPollApp.notifyShort(R.string.registration_password_mismatch_msg);
        }
    }

    /**
     * Récupère les actions faites depuis le clavier.
     * <p>
     * Récupère les actions faites depuis le clavier lors de l'édition des champs afin
     * de permettre de créer un nouveau compte depuis le bouton "Terminer" du clavier. (Cela évite à
     * l'utilisateur de devoir fermer le clavier et de cliquer sur le bouton créer un compte).
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // L'attribut android:imeOptions="actionNext" est défini dans le fichier xml de layout
        // (activity_registration.xml), L'actionId attendue est donc IME_ACTION_NEXT.
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            register(v);
            return true;
        }
        return false;
    }
}
