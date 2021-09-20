package com.example.boundsonglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayMusicActivity extends AppCompatActivity {
    private Button mBtnStart, mBtnPlay, mBtnStop, mBtnPause;
    private String MusicName; private int MusicImageId;
    private TextView mTvMusicname;
    private ImageView mIvMusicImage;
    private MusicService musicService;
    private  Intent serviceIntent;
    private boolean isServiceStarted = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.ServiceBinder serviceBinder = (MusicService.ServiceBinder) service;
            musicService = serviceBinder.getMusicService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        Intent intent = getIntent();
        MusicName = intent.getStringExtra("MusicName");
       MusicImageId= intent.getIntExtra("MusicImage",1);
       initviews(intent);
    }

    private void initviews(Intent intent) {
       mTvMusicname = findViewById(R.id.tvMusicName2);
       mIvMusicImage = findViewById(R.id.ivMusicImage2);
        mBtnStart = findViewById(R.id.btnStartService);
        mBtnStop = findViewById(R.id.btnStopService);
        mBtnPlay = findViewById(R.id.btnPlay);
        mBtnPause = findViewById(R.id.btnPause);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  serviceIntent = new Intent(PlayMusicActivity.this,MusicService.class);
      serviceIntent.putExtra("MusicNameFrom",intent.getStringExtra("MusicName"));
      bindService(serviceIntent,serviceConnection,BIND_AUTO_CREATE);
      isServiceStarted = true;
            }
        });
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isServiceStarted) musicService.play();
                Toast.makeText(PlayMusicActivity.this,"Music Playing",Toast.LENGTH_SHORT).show();
            }
        });
        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceStarted) musicService.pause();
                Toast.makeText(PlayMusicActivity.this,"Music paused",Toast.LENGTH_SHORT).show();
            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               stopService(serviceIntent);
               Toast.makeText(PlayMusicActivity.this,"Service Stopped",Toast.LENGTH_SHORT).show();
           isServiceStarted = false;
            }
        });
    }


}