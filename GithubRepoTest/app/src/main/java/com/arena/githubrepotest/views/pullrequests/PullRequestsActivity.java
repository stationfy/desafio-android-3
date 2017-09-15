package com.arena.githubrepotest.views.pullrequests;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arena.githubrepotest.application.AppController;
import com.arena.githubrepotest.models.Repository;
import com.arena.githubrepotest.views.pullrequests.core.PullRequestsPresenter;
import com.arena.githubrepotest.views.pullrequests.core.PullRequestsView;
import com.arena.githubrepotest.views.pullrequests.dagger.DaggerPullRequestsComponent;
import com.arena.githubrepotest.views.pullrequests.dagger.PullRequestsModule;

import javax.inject.Inject;

public class PullRequestsActivity extends AppCompatActivity {

    @Inject
    PullRequestsView view;

    @Inject
    PullRequestsPresenter presenter;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Repository repository = (Repository) getIntent().getExtras().get("repository");
        setTitle(repository.getFullName());

        DaggerPullRequestsComponent.builder()
                .appComponent(AppController.getNetComponent())
                .pullRequestsModule(new PullRequestsModule(this, repository))
                .build()
                .inject(this);
        setContentView(view.view());

        presenter.onCreate(repository);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void openPullRequestInBrowser(final String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
