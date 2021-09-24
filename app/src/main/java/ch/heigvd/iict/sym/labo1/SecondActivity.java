package ch.heigvd.iict.sym.labo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle extras = getIntent().getExtras();
        String email = extras.getString("account");
        TextView accountView = (TextView) findViewById(R.id.account_text);
        if (extras != null) {
            accountView.setText(email);
            //The key argument here must match that used in the other activity
        } else {
            accountView.setText("Hello");
        }

    }
}