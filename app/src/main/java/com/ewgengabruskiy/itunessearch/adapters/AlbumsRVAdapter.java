package com.ewgengabruskiy.itunessearch.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ewgengabruskiy.itunessearch.R;
import com.ewgengabruskiy.itunessearch.presenter.MainPresenter;
import com.ewgengabruskiy.itunessearch.ui.AlbumDetailActivity;
import com.ewgengabruskiy.itunessearch.view.AlbumsRowView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsRVAdapter extends RecyclerView.Adapter<AlbumsRVAdapter.ViewHolder> {
    private MainPresenter.AlbumsListPresenter repoListPresenter;

    public AlbumsRVAdapter(MainPresenter.AlbumsListPresenter presenter) {
        this.repoListPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        repoListPresenter.bindRepoListRow(position, holder);
    }

    @Override
    public int getItemCount() {
        return repoListPresenter.getRepoCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements AlbumsRowView {

        @BindView(R.id.album_name) TextView albumNameTv;
        @BindView(R.id.artist_name) TextView artistNameTv;
//        @BindView(R.id.track_count) TextView trackCountTv;
        @BindView(R.id.artwork_small) ImageView artWorkSmallImg;
        @BindView(R.id.albums_item_row) LinearLayout layoutId;
        private int id;
        private String artistName;
        private String albumName;
        private String imgUrl;
        private String year;
        private String price;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            layoutId.setOnClickListener(view -> {
                Intent detail = new Intent(layoutId.getContext(), AlbumDetailActivity.class);
                detail.putExtra("album",id);
                detail.putExtra("artistName",artistName);
                detail.putExtra("url",imgUrl);
                detail.putExtra("albumName",albumName);
                detail.putExtra("year",year);
                detail.putExtra("price",price);
                layoutId.getContext().startActivity(detail);
            });
        }


        @Override
        public void setAlbumName(String title) {
            albumNameTv.setText(title);
            this.albumName = title;
        }

        @Override
        public void setArtistName(String artistName) {
            artistNameTv.setText(artistName);
            this.artistName = artistName;
        }

//        @Override
//        public void setTrackCount(int trackCount) {
//            trackCountTv.setText(String.valueOf(trackCount));
//        }

        @Override
        public void setImage(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl).into(artWorkSmallImg);
            this.imgUrl=imageUrl;
        }

        @Override
        public void setAlbum(int id) {
            this.id = id;
        }

        @Override
        public void setAlbumYear(String year) {
            this.year = year;
        }

        @Override
        public void setAlbumPrice(String price) {
            this.price = price;
        }
    }
}
