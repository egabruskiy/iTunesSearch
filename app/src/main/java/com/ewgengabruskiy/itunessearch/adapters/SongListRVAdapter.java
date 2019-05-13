package com.ewgengabruskiy.itunessearch.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ewgengabruskiy.itunessearch.R;
import com.ewgengabruskiy.itunessearch.presenter.AlbumDetailPresenter;
import com.ewgengabruskiy.itunessearch.view.SongListRowView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class SongListRVAdapter extends RecyclerView.Adapter<SongListRVAdapter.ViewHolder> {
    private AlbumDetailPresenter.TrackListPresenter repoListPresenter;

    public SongListRVAdapter(AlbumDetailPresenter.TrackListPresenter presenter) {
        this.repoListPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        repoListPresenter.bindRepoListRow(position, holder);
    }

    @Override
    public int getItemCount() {
        return repoListPresenter.getRepoCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements SongListRowView {

        @BindView(R.id.artist_name_song) TextView artistNameTv;
        @BindView(R.id.artwork_song) ImageView artWorkSongImg;
        @BindView(R.id.track_name) TextView trackNameTV;
        @BindView(R.id.track_duration) TextView trackTimeTV;
        @BindView(R.id.price_song) TextView trackPriceTV;

        public ViewHolder(View itemView) {
            super(itemView);
                    ButterKnife.bind(this, itemView);
        }

        @Override
        public void setArtistName(String artistName) {
            artistNameTv.setText(artistName);
        }

        @Override
        public void setTrackName(String trackName) {
            trackNameTV.setText(trackName);
        }

        @Override
        public void setTrackTime(String trackTime) {
            trackTimeTV.setText(trackTime);
        }

        @Override
        public void setPrice(String price) {
            trackPriceTV.setText("US $" + price);
        }

        @Override
        public void setImage(String url) {
            Glide.with(itemView.getContext()).load(url).into(artWorkSongImg);
        }

    }
}
