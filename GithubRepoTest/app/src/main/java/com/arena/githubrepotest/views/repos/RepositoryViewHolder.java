package com.arena.githubrepotest.views.repos;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arena.githubrepotest.R;
import com.arena.githubrepotest.models.Repository;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.subjects.PublishSubject;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.circleImageViewAvatar)
    CircleImageView circleImageViewAvatar;
    @BindView(R.id.textViewRepositoryName)
    TextView textViewRepositoryName;
    @BindView(R.id.textViewRepositoryDescription)
    TextView textViewRepositoryDescription;
    @BindView(R.id.textViewForks)
    TextView textViewForks;
    @BindView(R.id.textViewStars)
    TextView textViewStars;
    @BindView(R.id.textViewOwnerName)
    TextView textViewOwnerName;

    private View view;

    public RepositoryViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition())
        );
    }

    void bind(Repository repository) {
        Glide.with(view.getContext()).load(repository.getOwner().getAvatarUrl()).into(circleImageViewAvatar);

        textViewRepositoryName.setText(repository.getName());
        textViewRepositoryDescription.setText(repository.getDescription());
        textViewForks.setText(String.valueOf(repository.getForkCount()));
        textViewStars.setText(String.valueOf(repository.getStars()));
        textViewOwnerName.setText(repository.getOwner().getName());
    }
}