package com.ewgengabruskiy.itunessearch.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.ewgengabruskiy.itunessearch.model.AlbumDetailResultModel;
import com.ewgengabruskiy.itunessearch.model.DataRequest;
import com.ewgengabruskiy.itunessearch.view.AlbumDetailView;
import com.ewgengabruskiy.itunessearch.view.SongListRowView;

import io.reactivex.Scheduler;


@InjectViewState
public class AlbumDetailPresenter extends MvpPresenter<AlbumDetailView> {

    public AlbumDetailPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
        dataRequest = new DataRequest();
    }

    private AlbumDetailResultModel detailResultModel;
    private Scheduler scheduler;
    private DataRequest dataRequest;
    private TrackListPresenter trackListPresenter = new TrackListPresenter();
    private String collectionId ;

    public class TrackListPresenter {
        public void bindRepoListRow(int pos, SongListRowView rowView) {
            if (detailResultModel != null) {
                rowView.setTrackName(detailResultModel.getTracks().get(pos+1).getTrackName());
                rowView.setArtistName(detailResultModel.getTracks().get(pos+1).getArtistName());
                rowView.setPrice(String.valueOf(detailResultModel.getTracks().get(pos+1).getTrackPrice()));
                rowView.setTrackTime(detailResultModel.getTracks().get(pos+1).getTime());
                rowView.setImage(detailResultModel.getTracks().get(pos+1).getArtworkUrl30());
            }
        }
        public int getRepoCount() {
            return detailResultModel == null ? 0 : detailResultModel.getResultCount()-1;
        }
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();

    }

    @SuppressLint("CheckResult")
    public void loadTracks(){
        dataRequest.getAlbum(collectionId)
                .observeOn(scheduler)
                .subscribe(resultModel -> {
                    this.detailResultModel = resultModel;
                    getViewState().updateSongList();
                });
    }


    public void setID(int id){
       collectionId = String.valueOf(id);
    }

    public TrackListPresenter getTrackListPresenter() {
        return trackListPresenter;
    }
}
