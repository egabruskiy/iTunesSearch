package com.ewgengabruskiy.itunessearch.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.ewgengabruskiy.itunessearch.model.DataRequest;
import com.ewgengabruskiy.itunessearch.model.AlbumsResultModel;
import com.ewgengabruskiy.itunessearch.view.AlbumsRowView;
import com.ewgengabruskiy.itunessearch.view.MainView;

import io.reactivex.Scheduler;


@InjectViewState

public class MainPresenter extends MvpPresenter<MainView> {

    private AlbumsResultModel resultModel;
    private Scheduler scheduler;
    private DataRequest dataRequest;
    private AlbumsListPresenter albumsListPresenter = new AlbumsListPresenter();



    public class AlbumsListPresenter {
        public void bindRepoListRow(int pos, AlbumsRowView rowView) {

            if (resultModel != null) {
                rowView.setAlbumName(resultModel.getAlbums().get(pos).getCollectionName());
                rowView.setArtistName(resultModel.getAlbums().get(pos).getArtistName());
//                rowView.setTrackCount(resultModel.getAlbums().get(pos).getTrackCount());
                rowView.setImage(resultModel.getAlbums().get(pos).getArtworkUrl100());
                rowView.setAlbum((resultModel.getAlbums().get(pos).getCollectionId()));
                rowView.setAlbumYear((resultModel.getAlbums().get(pos).getReleaseDate()));
                rowView.setAlbumPrice((resultModel.getAlbums().get(pos).getCollectionPrice()));
            }
        }

        public int getRepoCount() {
            return resultModel == null ? 0 : resultModel.getResultCount();
        }
    }

    public MainPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
        dataRequest= new DataRequest();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();

    }

    @SuppressLint("CheckResult")
    public void loadAlbums(String query){
        dataRequest.getAlbums(query)
                .observeOn(scheduler)
                .subscribe(resultModel -> {
                    this.resultModel = resultModel;
                    getViewState().updateAlbumList();
                }
                , throwable -> {
                    getViewState().showError(throwable.getMessage());
                });
    }


    public AlbumsListPresenter getAlbumsListPresenter() {
        return albumsListPresenter;
    }
}
