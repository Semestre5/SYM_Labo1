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
            Util.initCancelButton(email, password);
        });
        validateButton.setOnClickListener(view -> {
            //on réinitialise les messages d'erreur
            Util.resetError(email, password);

            //on récupère le contenu de deux champs dans des variables de type String
            String emailInput = email.getText().toString();
            String passwordInput = password.getText().toString();

            if(Util.checkCredentialValidity(email, password, emailInput, passwordInput, TAG,
                    NewAccountActivity.this)) {
                Intent activityOutput = new Intent();
                activityOutput.putExtra("newEmail", emailInput);
                activityOutput.putExtra("newPassword", passwordInput);
                setResult(RESULT_OK, activityOutput);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}