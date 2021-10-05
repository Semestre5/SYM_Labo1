package ch.heigvd.iict.sym.labo1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    private List<Pair<String, String>> credentials = new ArrayList<>(Arrays.asList(
                                                        new Pair<>("user1@heig-vd.ch","1234"),
                                                        new Pair<>("user2@heig-vd.ch","abcd")));

    private EditText email;
    private EditText password;
    private Button cancelButton;
    private Button validateButton;
    private TextView newAccountButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        // on définit le layout à utiliser pour l'affichage
        setContentView(R.layout.activity_main);

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_password);
        cancelButton = findViewById(R.id.main_cancel);
        validateButton = findViewById(R.id.main_validate);
        newAccountButton = findViewById(R.id.main_new_account);

        //mise en place des événements
        cancelButton.setOnClickListener(view -> {
            Util.initCancelButton(email, password);
        });

        newAccountButton.setOnClickListener(view -> {
            Intent switchActivityIntent = new Intent(this, NewAccountActivity.class);
            startActivityForResult(switchActivityIntent, Intent.FILL_IN_ACTION);
        });

        validateButton.setOnClickListener(view -> {
            //on réinitialise les messages d'erreur
            Util.resetError(email, password);

            //on récupère le contenu de deux champs dans des variables de type String
            String emailInput = email.getText().toString();
            String passwordInput = password.getText().toString();

            if (Util.checkCredentialValidity(email, password, emailInput, passwordInput, TAG, MainActivity.this)){
                for (Pair<String, String> cred: credentials) {
                    if (cred.first.equals(emailInput)) {
                        if (!cred.second.equals(passwordInput)) {
                            generateCredentialAlert().show();
                        } else {
                            Intent switchActivityIntent = new Intent(this, PostCoActivity.class);
                            switchActivityIntent.putExtra("account", emailInput);
                            startActivity(switchActivityIntent);
                            return;
                        }
                    }
                }
                generateCredentialAlert().show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");

    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Intent.FILL_IN_ACTION) {
            if (resultCode == RESULT_OK) {
                Pair<String, String> newCreds = new Pair<>(data.getStringExtra("newEmail"),
                        data.getStringExtra("newPassword"));
                credentials.add(newCreds);
            }
        }
    }

    private AlertDialog generateCredentialAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getString(R.string.main_credentials_err));
        // Add the buttons
        builder.setPositiveButton(getString(R.string.main_ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }


}

