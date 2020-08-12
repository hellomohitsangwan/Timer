package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    boolean counterIsActive = false;
    Button startButton;
    CountDownTimer CountDownTimer;
    MediaPlayer mediaPlayer;
    public void buttonClicked(View view) {
        if (counterIsActive) {

        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        CountDownTimer.cancel();
        startButton.setText("start again");
       // mediaPlayer.pause();
        counterIsActive = false;

        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("stop");

            CountDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();

                }
            }.start(); }
        }
        public void updateTimer ( int secondsLeft){

            int minutes = secondsLeft / 60;
            int seconds = secondsLeft - (minutes * 60);
            String secondString = Integer.toString(seconds);
            if (seconds <= 9) {

                secondString = "0" + seconds;

            }
            timerTextView.setText(Integer.toString(minutes) + " : " + secondString);

        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        timerSeekBar = findViewById(R.id.timerSeekBar);
         timerTextView = findViewById(R.id.countdownTextView);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i , boolean fromUser) {

                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
