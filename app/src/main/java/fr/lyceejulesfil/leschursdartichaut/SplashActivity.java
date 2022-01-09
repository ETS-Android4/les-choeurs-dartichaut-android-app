package fr.lyceejulesfil.leschursdartichaut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity{
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spashactivity);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, fr.lyceejulesfil.leschursdartichaut.MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
