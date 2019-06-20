package com.example.wbs;

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
    ImageButton playButton,continueButton,pauseButton,repeatButton;
    int videoNumber;
    String VName="funny";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);

        videoNumber = (int) getIntent().getExtras().getSerializable("videoNumber");

        ArrayList videos = JsonUtil.readVideoFromJson(VideoScreenActivity.this);

        for(int i=0; i<videos.size(); i++){
            VideoClass video= (VideoClass) videos.get(i);
            if(video.getId()==videoNumber){
                VName= video.getName();
            }
        }


        final Animation animAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);

        videoView = findViewById(R.id.videoViewID);

        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + getResources().getIdentifier(VName,"raw",""+getPackageName()));


        
        playButton =findViewById(R.id.playButtonID);

        continueButton = findViewById(R.id.continueButtonID);

        pauseButton = findViewById(R.id.pauseButtonID);

        repeatButton = findViewById(R.id.repeatButtonID);

        Log.i("test","Vor allem  "+pauseButton.getVisibility());
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playing=false;
                repeatButton.setVisibility(View.VISIBLE);
                continueButton.setVisibility(View.VISIBLE);
                started=false;
            }
        });

        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (!started) {
                    playButton.setVisibility(View.GONE);
                    playing = true;
                    started = true;
                    videoView.start();
                } else {
                    if (!playing) {
                        Log.i("test", "play play");
                        videoView.start();
                        pauseButton.clearAnimation();
                        pauseButton.setVisibility(View.GONE);
                        playing = true;
                        playButton.setVisibility(View.VISIBLE);
                        playButton.startAnimation(animAlpha);
                        playButton.setVisibility(View.GONE);

                    } else {
                        Log.i("test", "play stop");
                        playButton.clearAnimation();
                        playButton.setVisibility(View.GONE);
                        Log.i("test",""+pauseButton.getVisibility());
                        pauseButton.setVisibility(View.VISIBLE);
                        videoView.pause();
                        Log.i("test",""+pauseButton.getVisibility());
                        pauseButton.startAnimation(animAlpha);
                        playing = false;
                        pauseButton.setVisibility(View.GONE);

                    }
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("test","Pause play");
                videoView.start();
                pauseButton.clearAnimation();
                pauseButton.setVisibility(View.GONE);
                Log.i("test",""+pauseButton.getVisibility());
                playing=true;
                started=true;
                playButton.setVisibility(View.VISIBLE);
                playButton.startAnimation(animAlpha);
                playButton.setVisibility(View.GONE);
            }
        });


        videoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(started) {
                    if (playing) {
                        Log.i("test", "VView stop");

                        playButton.clearAnimation();
                        videoView.pause();
                        playing = false;
                        Log.i("test",""+pauseButton.getVisibility());
                        pauseButton.setVisibility(View.VISIBLE);
                        Log.i("test",""+pauseButton.getVisibility());
                        pauseButton.startAnimation(animAlpha);
                        pauseButton.setVisibility(View.GONE);
                    } else {
                        Log.i("test", "VView play");

                        pauseButton.clearAnimation();
                        videoView.start();
                        playing = true;
                        playButton.setVisibility(View.VISIBLE);
                        playButton.startAnimation(animAlpha);
                        playButton.setVisibility(View.GONE);
                    }
                }
            }
        } );
    }
}

