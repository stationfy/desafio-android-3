package com.arena.githubrepotest.views.pullrequests.core;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.arena.githubrepotest.R;
import com.arena.githubrepotest.models.PullRequest;
import com.arena.githubrepotest.models.Repository;
import com.arena.githubrepotest.views.pullrequests.PullRequestsActivity;
import com.arena.githubrepotest.views.pullrequests.PullRequestsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

public class PullRequestsView {

    @BindView(R.id.listPullRequests)
    RecyclerView listPullRequests;

    View view;
    PullRequestsAdapter adapter;

    public PullRequestsView(PullRequestsActivity context, Repository repository) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_pull_requests, parent, true);
        ButterKnife.bind(this, view);

        adapter = new PullRequestsAdapter();

        listPullRequests.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        listPullRequests.setLayoutManager(mLayoutManager);
        listPullRequests.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    public Observable<Integer> itemClicks()
    {
        return adapter.observeClicks();
    }

    public View view() {
        return view;
    }

    public void swapAdapter(ArrayList<PullRequest> pullRequests)
    {
        adapter.swapAdapter(pullRequests);
    }
}
