package com.example.groupproject.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.groupproject.Activities.LoginRegister;
import com.example.groupproject.MainActivity;
import com.example.groupproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_DURATION = 3000; //3000 is the delayed time in milliseconds.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        startAnimations();

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
//                //This method will be executed once the timer is over
//                // Start your app main activity
//                Intent i = new Intent(SplashScreen.this, LoginRegister.class);
//                startActivity(i);
//                // close this activity
//                finish();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashScreen.this, LoginRegister.class));
                }
            }
        }, SPLASH_TIME_DURATION);
    }

    private void startAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splashaplha);
        anim.reset();
        ConstraintLayout constraintLayout= findViewById(R.id.constraint_layout);
        constraintLayout.clearAnimation();
        constraintLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.splashanimations);
        anim.reset();
        ImageView view = (ImageView) findViewById(R.id.splashLogo);
        view.clearAnimation();
        view.startAnimation(anim);

    }
}