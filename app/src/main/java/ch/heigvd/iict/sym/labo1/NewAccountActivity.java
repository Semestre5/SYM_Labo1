package ch.heigvd.iict.sym.labo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.heigvd.iict.sym.labo1.Util;

public class NewAccountActivity extends AppCompatActivity {

    private static final String TAG = NewAccountActivity.class.getSimpleName();

    private EditText email;
    private EditText password;
    private Button cancelButton;
    private Button validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_password);
        cancelButton = findViewById(R.id.main_cancel);
        validateButton = findViewById(R.id.main_validate);

        //mise en place des événements
        cancelButton.setOnClickListener(view -> {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.getText().clear();
            password.getText().clear();
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.setError(null);
            password.setError(null);
        });
        validateButton.setOnClickListener(view -> {
            //on réinitialise les messages d'erreur
            email.setError(null);
            password.setError(null);

            //on récupère le contenu de deux champs dans des variables de type String
            String emailInput = email.getText().toString();
            String passwordInput = password.getText().toString();

            if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                // on affiche un message dans les logs de l'application
                Log.d(TAG, "Au moins un des deux champs est vide");
                // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
                // la méthode getString permet de charger un String depuis les ressources de
                // l'application à partir de son id
                if (emailInput.isEmpty())
                    email.setError(getString(R.string.main_mandatory_field));
                if (passwordInput.isEmpty())
                    password.setError(getString(R.string.main_mandatory_field));
            } else if (!Util.isValidAddress(emailInput)) {
                Toast toast = Toast.makeText(NewAccountActivity.this,
                        getString(R.string.main_invalid_email), Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent activityOutput = new Intent();
                activityOutput.putExtra("newEmail", emailInput);
                activityOutput.putExtra("newPassword", passwordInput);
                setResult(RESULT_OK, activityOutput);
                finish();
            }
        });
    }
}