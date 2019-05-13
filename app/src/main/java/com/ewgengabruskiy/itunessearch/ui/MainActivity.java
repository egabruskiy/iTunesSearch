package com.ewgengabruskiy.itunessearch.ui;

import android.app.SearchManager;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.ewgengabruskiy.itunessearch.R;
import com.ewgengabruskiy.itunessearch.adapters.AlbumsRVAdapter;
import com.ewgengabruskiy.itunessearch.presenter.MainPresenter;
import com.ewgengabruskiy.itunessearch.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @BindView(R.id.albums_rv) RecyclerView albumsRecyclerView;
    @BindView(R.id.show_error) TextView errorTv;
    AlbumsRVAdapter adapter;

    @InjectPresenter
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        albumsRecyclerView.setLayoutManager(new GridLayoutManager(this,3));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search for albums");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.loadAlbums(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }


    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        return new MainPresenter(AndroidSchedulers.mainThread());
    }


    @Override
    public void updateAlbumList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void init() {
        adapter = new AlbumsRVAdapter(presenter.getAlbumsListPresenter());
        albumsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        errorTv.setText(error);
    }
}
