package ch.heigvd.iict.sym.labo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import ch.heigvd.iict.sym.labo1.network.ImageDownloader;

public class PostCoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postco);
        Bundle extras = getIntent().getExtras();
        String email = extras.getString("account");
        TextView accountView = (TextView) findViewById(R.id.account_text);
        accountView.setText(email);
        //The key argument here must match that used in the other activity
        ImageView connected_image = (ImageView) findViewById(R.id.connected_image);
        new ImageDownloader(connected_image, "https://thispersondoesnotexist.com/image").show();
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