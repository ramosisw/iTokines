package mx.itson.iToquines.vistas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import mx.itson.iToquines.R;

public class Splash extends Activity {
    private static final int SPLASH_DURATION = 3000; // 2 seconds

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        //this.overridePendingTransition(R.anim.zoomout, R.anim.zoomin);
        Handler handler = new Handler();
        StartAnimations();
        // run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // make sure we close the splash screen so the user won't come back when it presses back key
                finish();
                // start the home screen if the back button wasn't pressed already
                Intent intent = new Intent(Splash.this, Main.class);
                Splash.this.startActivity(intent);

            }
        }, SPLASH_DURATION); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called
    }

    private void StartAnimations() {

    }

}
