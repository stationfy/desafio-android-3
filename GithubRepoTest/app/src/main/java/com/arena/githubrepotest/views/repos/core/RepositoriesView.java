package com.arena.githubrepotest.views.repos.core;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arena.githubrepotest.R;
import com.arena.githubrepotest.models.Repository;
import com.arena.githubrepotest.views.repos.RepositoriesAdapter;
import com.arena.githubrepotest.views.repos.RepositoriesListActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import rx.Observable;

public class RepositoriesView {

    @BindView(R.id.listRepositories)
    RecyclerView listRepositories;

    View view;
    RepositoriesAdapter adapter;

    public RepositoriesView(RepositoriesListActivity context ) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_repository_list, parent, true);
        ButterKnife.bind(this, view);

        adapter = new RepositoriesAdapter();

        listRepositories.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        listRepositories.setLayoutManager(mLayoutManager);
        listRepositories.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    public Observable<Integer> itemClicks()
    {
        return adapter.observeClicks();
    }

    public View view() {
        return view;
    }

    public void swapAdapter(ArrayList<Repository> repositories)
    {
        adapter.swapAdapter(repositories);
    }

}
