package com.example.boundsonglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder> {
    ArrayList<MusicModel> list;
    OnSongClicked onSongClicked;

    public MusicAdapter(ArrayList<MusicModel> list,OnSongClicked onSongClicked) {
        this.list = list;
        this.onSongClicked = onSongClicked;
    }

    @NonNull

    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MusicViewHolder(view,onSongClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
MusicModel musicModel = list.get(position);
holder.setData(musicModel);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
