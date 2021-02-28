package study.ltdd.gametinhtoan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    Animation logoAnimation;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //chạy chế độ full màn hình
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        mapping();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent SlashScreenIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(SlashScreenIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
        //Animation logo
        logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        ivLogo.setAnimation(logoAnimation);

        SharedPreferences sharedPreferences = getSharedPreferences("switchL"
        , MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("value", true)){
            changeLanguage("vi");
        }
    }

    private void mapping(){
        ivLogo = findViewById(R.id.ivLogo);
    }

    private void changeLanguage(String Language) {
        Locale locale = new Locale(Language);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
    }
}