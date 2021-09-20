package com.example.boundsonglist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MusicViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIvSongImage;
    private TextView mTvSongName;
    private CardView mSongCard;
    private OnSongClicked onSongClicked;


    public MusicViewHolder(@NonNull View itemView,OnSongClicked onSongClicked) {
        super(itemView);
        initView(itemView);
        this.onSongClicked = onSongClicked;
    }

    private void initView(View itemView) {
        mIvSongImage = itemView.findViewById(R.id.ivMusicImage);
        mTvSongName = itemView.findViewById(R.id.tvMusicName);
        mSongCard = itemView.findViewById(R.id.songcard);

    }

    public void setData(MusicModel musicModel) {
        mIvSongImage.setImageResource(musicModel.getImage());
        mTvSongName.setText(musicModel.getName());
        mSongCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSongClicked.onSongclicked(musicModel,getAdapterPosition());
            }
        });

    }
}