package ch.heigvd.iict.sym.labo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import ch.heigvd.iict.sym.labo1.network.ImageDownloader;

public class PostCoActivity extends AppCompatActivity {

    private static final String TAG = PostCoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
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
}