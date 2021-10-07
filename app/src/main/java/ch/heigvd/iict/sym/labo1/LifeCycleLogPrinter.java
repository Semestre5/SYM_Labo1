package ch.heigvd.iict.sym.labo1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class LifeCycleLogPrinter extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.getClass().getSimpleName(), "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(this.getClass().getSimpleName(), "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(this.getClass().getSimpleName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.getClass().getSimpleName(), "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(this.getClass().getSimpleName(), "onDestroy");

    }
}
