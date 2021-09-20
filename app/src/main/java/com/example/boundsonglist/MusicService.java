package com.example.boundsonglist;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Objects;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showNotificationAndStartForeGround();
    }



    @Override
    public IBinder onBind(Intent intent) {
        if (!(intent.getStringExtra("MusicNameFrom")==null)){
            switch (intent.getStringExtra("MusicNameFrom")){
                case "Naruto":
                    mediaPlayer = MediaPlayer.create(this,R.raw.naruto);
                    break;
                case "Pein":
                    mediaPlayer = MediaPlayer.create(this,R.raw.pein);
                    break;
                case "Kakashi":
                    mediaPlayer = MediaPlayer.create(this,R.raw.kakashi);
                    break;
                case "Itachi":
                    mediaPlayer = MediaPlayer.create(this,R.raw.itachi);
            break;
                case "Jiraya":
                    mediaPlayer = MediaPlayer.create(this,R.raw.jiraya);
            }
        }
        return new ServiceBinder();
    }


    public void play(){
      if (!mediaPlayer.isPlaying()){
          mediaPlayer.start();
      }
    }

    public void pause(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }


    public class ServiceBinder extends Binder{
        public MusicService getMusicService(){
            return MusicService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Service Stoped",Toast.LENGTH_SHORT).show();
    }
    private void showNotificationAndStartForeGround() {
        createChannel();
        NotificationCompat.Builder notificationBuilder = null;
        notificationBuilder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("Music App is running ")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Notification notification = null;
        notification = notificationBuilder.build();
        startForeground(120, notification);
    }
    public void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Notifications");
            Objects.requireNonNull(this.getSystemService(NotificationManager.class)).createNotificationChannel(channel);
        }
    }
}