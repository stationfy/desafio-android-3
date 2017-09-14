package com.arena.githubrepotest.views.repos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arena.githubrepotest.R;
import com.arena.githubrepotest.models.Repository;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.PublishSubject;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    ArrayList<Repository> listRepositories = new ArrayList<>();

    public void swapAdapter(ArrayList<Repository> repositories) {
        this.listRepositories.clear();
        this.listRepositories.addAll(repositories);
        notifyDataSetChanged();
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(view, itemClicks);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = listRepositories.get(position);
        holder.bind(repository);
    }

    @Override
    public int getItemCount() {
        if (listRepositories != null && listRepositories.size() > 0) {
            return listRepositories.size();
        } else {
            return 0;
        }
    }
}
