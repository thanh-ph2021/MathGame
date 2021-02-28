package study.ltdd.gametinhtoan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;

public class OptionActivity extends AppCompatActivity {
    Switch sLanguage, sDarkMode;
    Button btnNo, btnYes;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_option);

        // init dialog
        dialog = new Dialog(OptionActivity.this);
        dialog.setContentView(R.layout.dialog_laguage);
        mapping();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.bg_dialog));
        }
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(sLanguage.isChecked()){
                    sLanguage.setChecked(false);
                } else {
                    sLanguage.setChecked(true);
                }
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sLanguage.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("switchL"
                            , MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    sLanguage.setChecked(true);
                    changeLanguage("en");

                } else {
                    dialog.show();
                    SharedPreferences.Editor editor = getSharedPreferences("switchL"
                            , MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    sLanguage.setChecked(false);
                    changeLanguage("vi");
                }
                dialog.dismiss();
            }
        });

        //init SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("switchL"
                , MODE_PRIVATE);
        sLanguage.setChecked(sharedPreferences.getBoolean("value", true));


        sLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        sDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sDarkMode.isChecked()){
                    Toast.makeText(OptionActivity.this, "hello", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OptionActivity.this, "hi", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void mapping(){
        sLanguage = findViewById(R.id.switchLanguage);
        sDarkMode = findViewById(R.id.switchDarkMode);
        btnNo = dialog.findViewById(R.id.btnNo);
        btnYes = dialog.findViewById(R.id.btnYes);
    }

    private void changeLanguage(String Language){
        Locale locale = new Locale(Language);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
        this.finish();
        Intent intent = new Intent(OptionActivity.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}