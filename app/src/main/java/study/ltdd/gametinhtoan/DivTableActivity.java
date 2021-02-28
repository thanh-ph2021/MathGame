package study.ltdd.gametinhtoan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DivTableActivity extends AppCompatActivity {
    TextView txtQuestion, txtAnsA, txtAnsB, txtAnsC, txtAnsD, txtScore, txtMaxScore, txtScoreGameOver,
            txtMScoreGameOver;
    ProgressBar progressBarTimer;
    LinearLayout llGameOver, llBackground, llSetting;
    Button btnPlayAgain, btnResume, btnTraining1, btnTraining2, btnOption, btnQuit;
    ImageView imgSetting;
    CountDownTimer timer;
    Database database = HomeActivity.database;
    int maxScore;
    private static final int MAX_STREAMS = 5;
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private SoundPool soundPool;
    int mpButton;
    boolean loaded;
    float volume;

    Game g = new Game();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_div_table);
        mapping();
        llGameOver.setVisibility(View.GONE);
        llSetting.setVisibility(View.GONE);

        new CreateTable().execute();

        txtMaxScore.setText(String.valueOf(maxScore));
        txtScore.setText("0");

        startGame();

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // play sound button
                new playAudioButton().execute();
                //
                TextView txtClick = (TextView) v;
                double answerSelected = Double.parseDouble(txtClick.getText().toString());

                if(!g.checkAnswer(answerSelected, 10 - progressBarTimer.getProgress())){
                    disEnableAns();
                    llBackground.setAlpha(0.05f);
                    showScoreWhenGameOver();
                    g.setScore(0);
                    g.setTotalQuestions(1);
                    llGameOver.setVisibility(View.VISIBLE);
                    llGameOver.setAlpha(1.0f);
                    new UpdateTable().execute();
                    timer.cancel();
                } else {
                    txtScore.setText(String.valueOf(g.getScore()));
                    if(maxScore < g.getScore()){
                        maxScore = g.getScore();
                        txtMaxScore.setText(String.valueOf(maxScore));
                    }
                    startGame();
                }

            }
        };

        txtAnsA.setOnClickListener(answerButtonClickListener);
        txtAnsB.setOnClickListener(answerButtonClickListener);
        txtAnsC.setOnClickListener(answerButtonClickListener);
        txtAnsD.setOnClickListener(answerButtonClickListener);

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                txtScore.setText("0");
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disEnableAns();
                llBackground.setAlpha(0.05f);
                llSetting.setVisibility(View.VISIBLE);
                llSetting.setAlpha(1.0f);
                timer.cancel();
            }
        });

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableAns();
                llBackground.setAlpha(1.0f);
                llSetting.setVisibility(View.GONE);
                int time = 10 - progressBarTimer.getProgress();
                startTimer(time*1000);
            }
        });

        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent option = new Intent(DivTableActivity.this, OptionActivity.class);
                startActivity(option);
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        View.OnClickListener trainingButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent play = new Intent(DivTableActivity.this, TrainingActivity.class);
                startActivity(play);
            }
        };
        btnTraining1.setOnClickListener(trainingButtonClickListener);
        btnTraining2.setOnClickListener(trainingButtonClickListener);

        //audio click
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float currentVolumeIndex = (float)audioManager.getStreamVolume(streamType);
        float maxVolumeIndex = (float)audioManager.getStreamMaxVolume(streamType);
        volume = currentVolumeIndex / maxVolumeIndex;
        this.setVolumeControlStream(streamType);
        if (Build.VERSION.SDK_INT >= 21 ) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // for Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // When Sound Pool load complete.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        mpButton = soundPool.load(this, R.raw.au_button, 1);
    }

    public void mapping(){
        txtQuestion = findViewById(R.id.txtQuestion);
        txtAnsA = findViewById(R.id.txtAnsA);
        txtAnsB = findViewById(R.id.txtAnsB);
        txtAnsC = findViewById(R.id.txtAnsC);
        txtAnsD = findViewById(R.id.txtAnsD);
        txtScore = findViewById(R.id.txtScore);
        progressBarTimer = findViewById(R.id.progressBarTimer);
        llGameOver = findViewById(R.id.llGameOver);
        llBackground = findViewById(R.id.llBackground);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        imgSetting = findViewById(R.id.imgSetting);
        llSetting = findViewById(R.id.llSetting);
        btnResume = findViewById(R.id.btnResume);
        btnTraining1 = findViewById(R.id.btnTraining1);
        btnTraining2 = findViewById(R.id.btnTraining2);
        btnOption = findViewById(R.id.btnOption);
        btnQuit = findViewById(R.id.btnQuit);
        txtScoreGameOver = findViewById(R.id.txtScoreGameOver);
        txtMScoreGameOver = findViewById(R.id.txtMScoreGameOver);
        txtMaxScore = findViewById(R.id.txtMaxScore);
    }

    public void startGame(){
        if(timer != null){
            timer.cancel();
        }
        progressBarTimer.setProgress(0);
        startTimer(10000);
        llBackground.setAlpha(1.0f);
        llGameOver.setVisibility(View.GONE);
        enableAns();
        g.makeNewQuestion('/', 2);
        int [] answerArray = g.getCurrentQuestions().getAnswerArray();
        txtAnsA.setText(String.valueOf(answerArray[0]));
        txtAnsB.setText(String.valueOf(answerArray[1]));
        txtAnsC.setText(String.valueOf(answerArray[2]));
        txtAnsD.setText(String.valueOf(answerArray[3]));
        txtQuestion.setText(g.getCurrentQuestions().getQuestionPhrase());
    }

    private void startTimer(int time){
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondRemaining = progressBarTimer.getProgress();
                progressBarTimer.setProgress(secondRemaining+1);
            }

            @Override
            public void onFinish() {
                disEnableAns();
                llBackground.setAlpha(0.05f);
                showScoreWhenGameOver();
                g.setScore(0);
                g.setTotalQuestions(1);
                llGameOver.setVisibility(View.VISIBLE);
                llGameOver.setAlpha(1.0f);
                new UpdateTable().execute();
            }
        }.start();
    }

    private void enableAns(){
        txtAnsA.setEnabled(true);
        txtAnsB.setEnabled(true);
        txtAnsC.setEnabled(true);
        txtAnsD.setEnabled(true);
    }

    private void disEnableAns(){
        txtAnsA.setEnabled(false);
        txtAnsB.setEnabled(false);
        txtAnsC.setEnabled(false);
        txtAnsD.setEnabled(false);
    }

    private void showScoreWhenGameOver(){
        txtScoreGameOver.setText(txtScore.getText());
        txtMScoreGameOver.setText(txtMaxScore.getText());
    }

    private class playAudioButton extends AsyncTask<Void, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(loaded){
                float leftVolume = volume;
                float rightVolume = volume;
                int streamId = soundPool.play(mpButton, leftVolume, rightVolume, 1, 0, 1f);
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            return "Done";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private class CreateTable extends AsyncTask<Void, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            database.queryData("CREATE TABLE IF NOT EXISTS MaxScore(" +
                    "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "play INTEGER, " +
                    "addition INTEGER," +
                    "subtraction INTEGER," +
                    "multiplication INTEGER," +
                    "divide INTEGER," +
                    "modulo INTEGER," +
                    "multiTable INTEGER," +
                    "divTable INTEGER)");
            // show maxScore in SQLite
            Cursor dataMaxScore = database.getData("SELECT * FROM MaxScore");
            if(dataMaxScore.getCount() == 0) {
                database.queryData("INSERT INTO MaxScore VALUES(null, 0, 0, 0, 0, 0, 0, 0, 0)");
            } else {
                while (dataMaxScore.moveToNext()) {
                    maxScore = dataMaxScore.getInt(8);
                    break;
                }
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            return "Done";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private class UpdateTable extends AsyncTask<Void, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            database.queryData("UPDATE MaxScore SET divTable=" + maxScore + " WHERE Id = 1");
        }

        @Override
        protected String doInBackground(Void... voids) {
            return "Done";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}