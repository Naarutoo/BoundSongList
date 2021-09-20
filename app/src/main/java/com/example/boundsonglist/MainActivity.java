package com.example.boundsonglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnSongClicked {
private RecyclerView recyclerView;
ArrayList<MusicModel>list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    initview();
    buildList();
    setRecyclerView();
    }

    private void setRecyclerView() {
        MusicAdapter adapter = new MusicAdapter(list,this::onSongclicked);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void buildList() {
 MusicModel musicModel = new MusicModel(R.drawable.naruto,"Naruto");
 list.add(musicModel);
 MusicModel musicModel1 = new MusicModel(R.drawable.itachi,"Itachi");
 list.add(musicModel1);
 MusicModel musicModel2 = new MusicModel(R.drawable.pein,"Pein");
 list.add(musicModel2);
 MusicModel musicModel3 = new MusicModel(R.drawable.kakashi,"Kakashi");
 list.add(musicModel3);
 MusicModel musicModel4 = new MusicModel(R.drawable.jiraya,"Jiraya");
   list.add(musicModel4);
    }

    private void initview() {
    recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void onSongclicked(MusicModel musicModel, int position) {
        Intent intent = new Intent(MainActivity.this,PlayMusicActivity.class);
        intent.putExtra("MusicName",musicModel.getName());
        intent.putExtra("MusicImage",musicModel.getImage());
        startActivity(intent);
    }
}