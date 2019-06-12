package com.example.wbs;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.VideoView;

public class VideoScreenActivity extends AppCompatActivity {

    VideoView videoView;
    Boolean playing = false,started=false;
    ImageButton playButton,continueButton;
    int videoNumber;
    String VName="europe";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);

        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);

        videoView = findViewById(R.id.videoViewID);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + getResources().getIdentifier(VName,"raw",""+getPackageName()));

        //videoNumber = (int) getIntent().getExtras().getSerializable("videoNumber");



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
                if (!started) {
                    playButton.setImageResource(R.drawable.play);
                    videoView.start();
                    playButton.setVisibility(View.GONE);
                    playing=true;
                    started=true;
                }
            }
        });

        videoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(playing){
                    videoView.pause();
                    playing=false;
                    playButton.setImageResource(R.drawable.pause);
                    playButton.setVisibility(View.VISIBLE);
                    playButton.startAnimation(animAlpha);
                }
                else{
                    videoView.start();
                    playing =true;
                    playButton.setImageResource(R.drawable.play);
                    playButton.setVisibility(View.VISIBLE);
                    playButton.startAnimation(animAlpha);
                }
                playButton.setVisibility(View.GONE);
            }
        } );
    }
}

