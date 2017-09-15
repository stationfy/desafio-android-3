package com.arena.githubrepotest.views.pullrequests;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arena.githubrepotest.R;
import com.arena.githubrepotest.models.PullRequest;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.subjects.PublishSubject;

public class PullRequestViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.circleImageViewAvatar)
    CircleImageView circleImageViewAvatar;
    @BindView(R.id.textViewTitlePullRequest)
    TextView textViewTitlePullRequest;
    @BindView(R.id.textViewPullRequestDescription)
    TextView textViewPullRequestDescription;
    @BindView(R.id.textViewOwnerName)
    TextView textViewOwnerName;

    private View view;

    public PullRequestViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition()));
    }

    void bind(PullRequest pullRequest) {
        Glide.with(view.getContext()).load(pullRequest.getUser().getAvatarUrl()).into(circleImageViewAvatar);

        textViewTitlePullRequest.setText(pullRequest.getTitle());
        textViewPullRequestDescription.setText(pullRequest.getBody());
        textViewOwnerName.setText(pullRequest.getUser().getName());
    }
}