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
    ImageButton playButton,continueButton;
    Integer videoNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);
        videoNumber= (Integer) getIntent().getExtras().getSerializable("videoNumber");

        videoView = findViewById(R.id.videoViewID);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.europe);

        playButton =findViewById(R.id.playButtonID);
        continueButton = findViewById(R.id.continueButtonID);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playing=false;
                playButton.setVisibility(View.VISIBLE);
                playButton.setImageResource(R.drawable.repeat);
                continueButton.setVisibility(View.GONE);
            }
        });
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (playing == false) {
                    playButton.setImageResource(R.drawable.play);
                    videoView.start();
                    playButton.setVisibility(View.GONE);
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
                    playButton.setVisibility(View.VISIBLE);
                }
            }
        } );
    }
}

