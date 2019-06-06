package com.example.wbs;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class VideoScreenActivity extends AppCompatActivity {

    VideoView videoView;
    Boolean playing = false;
    ImageButton playbutton,continuebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);

        videoView = findViewById(R.id.videoViewID);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.europe);

        playbutton =findViewById(R.id.playButtonID);
        continuebutton = findViewById(R.id.continueButtonID);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playing=false;
                playbutton.setVisibility(View.VISIBLE);
                playbutton.setImageResource(R.drawable.repeat);
                continuebutton.setVisibility(View.GONE);
            }
        });
        playbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (playing == false) {
                    playbutton.setImageResource(R.drawable.play);
                    videoView.start();
                    playbutton.setVisibility(View.GONE);
                    playing = true;
                }
            }
        } );
        videoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(playing==true){
                    videoView.pause();
                    playing=false;
                    playbutton.setVisibility(View.VISIBLE);
                }
            }
        } );
    }
}

