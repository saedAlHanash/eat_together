package com.example.eattogether;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.eattogether.ui.Login;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {
    Animation animation ,animation1;
    @BindView(R.id.welcom_screen)
    ImageView welcomScreen;
    @BindView(R.id.sblash_screen_tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.sblash_screen_button_login)
    AppCompatButton btnLogin;
    @BindView(R.id.sblash_screen_button_signup)
    AppCompatButton btnSignup;
    @BindView(R.id.sblash_screen_tv_please_login)
    TextView tvPleaseLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        animation1 = AnimationUtils.loadAnimation(this,R.anim.test1);
        ObjectAnimator animation = ObjectAnimator.ofFloat(welcomScreen, "scaleX", 1, 1.6f);
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(welcomScreen, "scaleY", 1, 1.6f);
        animation.setDuration(1000);
        animation1.setDuration(1000);
        (new Handler()).postDelayed((Runnable) () -> {
            animation.start();
            animation1.start();
            tvWelcome.setAnimation(this.animation1);
            btnLogin.setVisibility(View.VISIBLE);
            btnSignup.setVisibility(View.VISIBLE);
            tvPleaseLogin.setVisibility(View.VISIBLE);
            tvPleaseLogin.setAnimation(this.animation);
            btnLogin.setAnimation(this.animation);
            btnSignup.setAnimation(this.animation);
            tvWelcome.setVisibility(View.INVISIBLE);
        }, 1000);

        btnSignup.setOnClickListener(v -> {
            Intent intent = new Intent(this,SignUp.class);
            startActivity(intent);
        });
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });
    }
}