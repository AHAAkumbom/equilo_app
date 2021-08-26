package com.example.equilo.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.equilo.R;
import com.example.equilo.User.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    //Varriables
    ImageView backgroundImage;
    TextView sloganName;

    //Animations
    Animation sideAnim,middleAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Hooks
        backgroundImage = findViewById(R.id.background_image);
        sloganName = findViewById(R.id. slogan_name);

        //Animations
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        middleAnim = AnimationUtils.loadAnimation(this,R.anim.middle_anim);

        //set Animation On elements

        backgroundImage.setAnimation(sideAnim);
        sloganName.setAnimation(middleAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
               boolean isFristTime = onBoardingScreen.getBoolean("fristTime",true);

               if(isFristTime){

                   SharedPreferences.Editor editor = onBoardingScreen.edit();
                   editor.putBoolean("fristTime",false);
                   editor.commit();

                   Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                   startActivity(intent);
                   finish();


               }
               else{
                   Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                   startActivity(intent);
                   finish();
               }


            };
        },SPLASH_TIMER);
    }
}