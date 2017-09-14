package com.arena.githubrepotest.views.repos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arena.githubrepotest.application.AppController;
import com.arena.githubrepotest.models.Repository;
import com.arena.githubrepotest.views.pullrequests.PullRequestsActivity;
import com.arena.githubrepotest.views.repos.core.RepositoriesPresenter;
import com.arena.githubrepotest.views.repos.core.RepositoriesView;
import com.arena.githubrepotest.views.repos.dagger.DaggerRepositoriesComponent;
import com.arena.githubrepotest.views.repos.dagger.RepositoriesModule;

import java.io.Serializable;

import javax.inject.Inject;

public class RepositoriesListActivity extends AppCompatActivity {

    @Inject
    RepositoriesView view;
    @Inject
    RepositoriesPresenter presenter;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerRepositoriesComponent.builder()
                .appComponent(AppController.getNetComponent())
                .repositoriesModule(new RepositoriesModule(this))
                .build()
                .inject(this);
        setContentView(view.view());

        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    public void goToPullRequests(final Repository repository) {
        Intent in = new Intent(this, PullRequestsActivity.class);
        in.putExtra("repository", (Serializable) repository);
        startActivity(in);
    }
}
