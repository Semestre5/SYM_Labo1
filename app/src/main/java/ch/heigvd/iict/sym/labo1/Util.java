package ch.heigvd.iict.sym.labo1;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.regex.Pattern;

public class Util {

    public static boolean isValidAddress(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static void displayInvalidAddressToast(AppCompatActivity activity, String toastText){
        Toast toast = Toast.makeText(activity, toastText, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static boolean checkCredentialValidity(EditText emailBox, EditText pwdBox, String emailIn, String pwdIn, String TAG, AppCompatActivity activity){
        if(emailIn.isEmpty() || pwdIn.isEmpty()) {
            // on affiche un message dans les logs de l'application
            Log.d(TAG, "Au moins un des deux champs est vide");
            // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
            // la méthode getString permet de charger un String depuis les ressources de
            // l'application à partir de son id
            if (emailIn.isEmpty())
                emailBox.setError(activity.getString(R.string.main_mandatory_field));
            if (pwdIn.isEmpty())
                pwdBox.setError(activity.getString(R.string.main_mandatory_field));
            return false;
        } else if (!Util.isValidAddress(emailIn)) {
            Util.displayInvalidAddressToast(activity,
                    activity.getString(R.string.main_invalid_email));
            return false;
        }
        return true;
    }

    public static void initCancelButton(EditText emailBox, EditText pwdBox){
        //on va vider les champs de la page de login lors du clique sur le bouton Cancel
        emailBox.getText().clear();
        pwdBox.getText().clear();
        // on annule les éventuels messages d'erreur présents sur les champs de saisie
        emailBox.setError(null);
        pwdBox.setError(null);
    }

    public static void resetError(EditText emailBox, EditText pwdBox){
        emailBox.setError(null);
        pwdBox.setError(null);
    }
}
