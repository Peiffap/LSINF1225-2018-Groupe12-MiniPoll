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

public class UpdateCredentialsActivity extends Activity implements TextView.OnEditorActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_credentials);
        TextView login = (TextView) this.findViewById(R.id.credentials_update_login);
        login.setText(User.getConnectedUser().getLogin());
    }

    /**
     * Vérifie les mots de passe et login et set les nouvelles valeurs.
     * <p>
     * Cette méthode est appelée grâce à l'attribut onClick indiqué dans le fichier xml de layout
     * sur le bouton de création de compte. Elle peut également être appelée depuis la méthode
     * "onEditorAction" de cette classe.
     *
     * @param v Une vue quelconque (n'est pas utilisé ici, mais requis par le onClick)
     */
    public void updateCredentials(View v) {
        EditText loginEditText = findViewById(R.id.credentials_update_login);
        String login = loginEditText.getText().toString();
        EditText newPasswordEditText = findViewById(R.id.credentials_update_new_password);
        String newPassword = newPasswordEditText.getText().toString();
        EditText oldPasswordEditText = findViewById(R.id.credentials_update_old_password);
        String oldPassword = oldPasswordEditText.getText().toString();
        EditText newPasswordConfirmationEditText = findViewById(R.id.credentials_update_new_password_confirmation);
        String newPasswordConfirmation = newPasswordConfirmationEditText.getText().toString();

        if (isValidField(login)) {
            ArrayList<User> users = User.getUsers();
            boolean existing = false;
            for (User u : users) {
                existing = u.getLogin().equals(login);
                if (existing && !login.equals(User.getConnectedUser().getLogin())) {
                    MiniPollApp.notifyShort(R.string.credentials_update_existing_login_msg);
                    break;
                }
            }
            if (!existing) {
                if (oldPassword.equals(User.getConnectedUser().getPassword())) {
                    if (newPassword.equals(newPasswordConfirmation)) {
                        if (newPassword.length() > 2 && !isNullOrWhitespace(newPassword)) {
                            User.getConnectedUser().setLogin(login);
                            User.getConnectedUser().setPassword(newPassword);
                        } else {
                            MiniPollApp.notifyShort(R.string.credentials_update_password_invalid_msg);
                        }
                    } else {
                        MiniPollApp.notifyShort(R.string.credentials_update_password_mismatch_msg);
                    }
                } else {
                    MiniPollApp.notifyShort(R.string.credentials_update_wrong_old_password_msg);
                }
            }
        } else {
            MiniPollApp.notifyShort(R.string.credentials_update_invalid_login_msg);
        }
    }

    /**
     * Récupère les actions faites depuis le clavier.
     * <p>
     * Récupère les actions faites depuis le clavier lors de l'édition des champs de texte afin
     * de permettre de modifier son login/mdp depuis le bouton "Terminer" du clavier. (Cela évite à
     * l'utilisateur de devoir fermer le clavier et de cliquer sur le bouton modifier mon login/mdp).
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // L'attribut android:imeOptions="actionNext" est défini dans le fichier xml de layout
        // (activity_update_profile.xml), L'actionId attendue est donc IME_ACTION_NEXT.
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            updateCredentials(v);
            return true;
        }
        return false;
    }
}
