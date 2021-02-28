package study.ltdd.gametinhtoan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Button btnPlay, btnTraining, btnOption;
    private MediaPlayer mpBackground;
    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        mapping();

        database = new Database(this, "db_game.db", null, 1);
        //audio background
        mpBackground = MediaPlayer.create(this, R.raw.au_background);
        new playSoundBackground().execute();


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //playSoundButton();
                Intent play = new Intent(HomeActivity.this, PlayActivity.class);
                startActivity(play);
            }
        });

        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent train = new Intent(HomeActivity.this, TrainingActivity.class);
                startActivity(train);
            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent option = new Intent(HomeActivity.this, OptionActivity.class);
                startActivity(option);
            }
        });

    }

    //ánh xạ
    private void mapping(){
        btnPlay = findViewById(R.id.btnPlay);
        btnTraining = findViewById(R.id.btnRenLuyen);
        btnOption = findViewById(R.id.btnOption);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mpBackground.pause();
    }

    private class playSoundBackground extends AsyncTask<Void, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mpBackground.start();
            mpBackground.setLooping(true);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return "done";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}