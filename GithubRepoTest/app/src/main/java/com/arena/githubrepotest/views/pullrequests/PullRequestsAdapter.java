package com.arena.githubrepotest.views.pullrequests;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arena.githubrepotest.R;
import com.arena.githubrepotest.models.PullRequest;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.PublishSubject;

public class PullRequestsAdapter extends RecyclerView.Adapter<PullRequestViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<PullRequest> listPullRequests = new ArrayList<>();

    public void swapAdapter(ArrayList<PullRequest> pullRequests) {
        this.listPullRequests.clear();
        this.listPullRequests.addAll(pullRequests);
        notifyDataSetChanged();
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pullrequest, parent, false);
        return new PullRequestViewHolder(view, itemClicks);
    }

    @Override
    public void onBindViewHolder(PullRequestViewHolder holder, int position) {
        PullRequest pullRequest = listPullRequests.get(position);
        holder.bind(pullRequest);
    }

    @Override
    public int getItemCount() {
        if (listPullRequests != null && listPullRequests.size() > 0) {
            return listPullRequests.size();
        } else {
            return 0;
        }
    }
}
