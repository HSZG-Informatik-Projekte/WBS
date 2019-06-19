package com.example.wbs;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.VideoView;

import java.util.ArrayList;

public class VideoScreenActivity extends AppCompatActivity {

    VideoView videoView;
    Boolean playing = false,started=false;
    ImageButton playButton,continueButton;
    int videoNumber;
    String VName="funny";
    int VQId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);

        videoNumber = (int) getIntent().getExtras().getSerializable("videoNumber");

        ArrayList videos = JsonUtil.readVideoFromJson(VideoScreenActivity.this);

        for(int i=0; i<videos.size(); i++){
            VideoClass video = (VideoClass) videos.get(i);
            Log.i("testId",""+video.getId());
            Log.i("testVNumber",""+videoNumber);
            Log.i("testName",""+video.getName());
            Log.i("testVName",""+VName);
            if(video.getId() == videoNumber){
                VName = video.getName();
                VQId = video.getQuestionid();
            }
        }
        Log.i("testVNameNeu",""+VName);

        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);

        videoView = findViewById(R.id.videoViewID);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + getResources().getIdentifier(VName,"raw",""+getPackageName()));


        
        playButton = findViewById(R.id.playButtonID);

        continueButton = findViewById(R.id.continueButtonID);
        continueButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent mySuperIntent = new Intent(VideoScreenActivity.this, ShowProfileActivity.class);;
                  mySuperIntent.putExtra("VQId", VQId);
                  startActivity(mySuperIntent);
                  finish();
              }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playing = false;
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

