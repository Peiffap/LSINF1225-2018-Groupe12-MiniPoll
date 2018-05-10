package be.lsinf1225gr12.minipoll.minipoll.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import be.lsinf1225gr12.minipoll.minipoll.MiniPollApp;
import be.lsinf1225gr12.minipoll.minipoll.R;
import be.lsinf1225gr12.minipoll.minipoll.model.User;

import static be.lsinf1225gr12.minipoll.minipoll.InputValidation.isNullOrWhitespace;
import static be.lsinf1225gr12.minipoll.minipoll.InputValidation.isValidEmail;
import static be.lsinf1225gr12.minipoll.minipoll.InputValidation.isValidField;
import be.lsinf1225gr12.minipoll.minipoll.model.User.*;

public class UpdateProfileActivity extends Activity implements TextView.OnEditorActionListener {

    final int REQ_CODE_PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        TextView fn = (TextView) this.findViewById(R.id.profile_update_first_name);
        fn.setText(User.getConnectedUser().getFirstname());
        TextView ln = (TextView) this.findViewById(R.id.profile_update_last_name);
        ln.setText(User.getConnectedUser().getName());
        TextView mail = (TextView) this.findViewById(R.id.profile_update_email);
        mail.setText(User.getConnectedUser().getMail());
    }

    /**
     * Vérifie que les champs sont cohérents et modifie le profil.
     * <p>
     * Cette méthode permet à l'utilisateur de modifier son profil. Si les champs sont cohérents, elle
     * affiche l'écran de menu, sinon un message est affiché à l'utilisateur.
     * <p>
     * Cette méthode est appelée grâce à l'attribut onClick indiqué dans le fichier xml de layout
     * sur le bouton de modification de compte. Elle peut également être appelée depuis la méthode
     * "onEditorAction" de cette classe.
     *
     * @param v Une vue quelconque (n'est pas utilisé ici, mais requis par le onClick)
     */
    public void updateProfile(View v) {
        EditText firstNameEditText = findViewById(R.id.profile_update_first_name);
        String firstName = firstNameEditText.getText().toString();
        EditText lastNameEditText = findViewById(R.id.profile_update_last_name);
        String lastName = lastNameEditText.getText().toString();
        EditText emailEditText = findViewById(R.id.profile_update_email);
        String email = emailEditText.getText().toString();

        if (isValidField(firstName)) {
            if (isValidField(lastName)) {
                if (isValidEmail(email)) {
                    User.getConnectedUser().setMail(email);
                    User.getConnectedUser().setFirstname(firstName);
                    User.getConnectedUser().setName(lastName);
                    MiniPollApp.notifyShort(R.string.profile_update_updated_msg);
                } else {
                    MiniPollApp.notifyShort(R.string.profile_update_invalid_email_msg);
                }
            } else {
                MiniPollApp.notifyShort(R.string.profile_update_invalid_last_name_msg);
            }
        } else {
            MiniPollApp.notifyShort(R.string.profile_update_invalid_first_name_msg);
        }
    }

    /**
     * Amène l'utilisateur vers l'écran de changement de login.
     * <p>
     * Cette méthode est appelée grâce à l'attribut onClick indiqué dans le fichier xml de layout
     * sur le bouton de modification de compte. Elle peut également être appelée depuis la méthode
     * "onEditorAction" de cette classe.
     *
     * @param v Une vue quelconque (n'est pas utilisé ici, mais requis par le onClick)
     */
    public void updateCredentials(View v) {
        Intent intent = new Intent(this, UpdateCredentialsActivity.class);
        startActivity(intent);
    }

    /**
     * Récupère les actions faites depuis le clavier.
     * <p>
     * Récupère les actions faites depuis le clavier lors de l'édition des champs de texte afin
     * de permettre de modifier son profil depuis le bouton "Terminer" du clavier. (Cela évite à
     * l'utilisateur de devoir fermer le clavier et de cliquer sur le bouton modifier mon profil).
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // L'attribut android:imeOptions="actionNext" est défini dans le fichier xml de layout
        // (activity_update_profile.xml), L'actionId attendue est donc IME_ACTION_NEXT.
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            updateProfile(v);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = (ImageView) findViewById(R.id.profile_pic);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void pickPic(View v) {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, REQ_CODE_PICK_IMAGE);
    }
}
