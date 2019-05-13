package com.ewgengabruskiy.itunessearch.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.ewgengabruskiy.itunessearch.R;
import com.ewgengabruskiy.itunessearch.adapters.SongListRVAdapter;
import com.ewgengabruskiy.itunessearch.presenter.AlbumDetailPresenter;
import com.ewgengabruskiy.itunessearch.view.AlbumDetailView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AlbumDetailActivity extends MvpAppCompatActivity implements AlbumDetailView {

    @BindView(R.id.track_list_rv) RecyclerView trackListRecyclerView;
    @BindView(R.id.artist_name_detail) TextView artistNameTv;
    @BindView(R.id.album_name_detail) TextView albumNameTv;
    @BindView(R.id.artwork_album_detail) ImageView imageView;
    @BindView(R.id.album_year_detail) TextView albumYearTv;
    @BindView(R.id.album_price_detail) TextView albumPriceTv;
    SongListRVAdapter adapter;

    @InjectPresenter
    AlbumDetailPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        ButterKnife.bind(this);
        trackListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trackListRecyclerView.setHasFixedSize(true);
        presenter.setID(getIntent().getIntExtra("album",-1));
        presenter.loadTracks();
        artistNameTv.setText(getIntent().getStringExtra("artistName"));
        albumNameTv.setText(getIntent().getStringExtra("albumName"));
        albumYearTv.setText(getIntent().getStringExtra("year"));
        albumPriceTv.setText(getIntent().getStringExtra("price"));
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("url")).into(imageView);
    }


    @ProvidePresenter
    public AlbumDetailPresenter providePresenter() {
        return new AlbumDetailPresenter(AndroidSchedulers.mainThread());
    }

    @Override
    public void updateSongList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void init() {
        adapter = new SongListRVAdapter(presenter.getTrackListPresenter());
        trackListRecyclerView.setAdapter(adapter);
    }


}
