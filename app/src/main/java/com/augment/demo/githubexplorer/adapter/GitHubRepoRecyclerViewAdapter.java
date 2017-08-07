package com.augment.demo.githubexplorer.adapter;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.augment.demo.githubexplorer.R;
import com.augment.demo.githubexplorer.view.ListRepositoryView;

import java.util.Collections;
import java.util.List;

import apollo.api.github.api.GitHubRepoQuery;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Othman
 * The ReecycleView Adapter to list the public repository
 * Items are selectable
 * click on one item show the repository detail
 */


public class GitHubRepoRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRepoRecyclerViewAdapter.RepoItemViewHolder> {

  private List<GitHubRepoQuery.Node> repos = Collections.emptyList();
  private ListRepositoryView navigator;

  public GitHubRepoRecyclerViewAdapter(ListRepositoryView view) {
    this.navigator = view;
  }

  public void setRepos(List<GitHubRepoQuery.Node> repos) {
    this.repos = repos;
    this.notifyDataSetChanged();
  }

  @Override
  public RepoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    final View itemView = layoutInflater.inflate(R.layout.item_github_item, parent, false);

    return new RepoItemViewHolder(itemView);
  }

  @Override public void onBindViewHolder(RepoItemViewHolder holder, int position) {
    final GitHubRepoQuery.Node repoEntry = this.repos.get(position);

          holder.setRepoItem(repoEntry, navigator);

  }

  @Override public int getItemCount() {
    return repos.size();
  }

  static class RepoItemViewHolder extends RecyclerView.ViewHolder {

      @BindView(R.id.tv_repository_name) TextView repositoryTitle;
      @BindView(R.id.tv_repository_short_description) TextView repositoryShortDescription;
      @BindView(R.id.tv_repository_language) TextView repositoryLanguage;
      @BindView(R.id.repo_item_container) View repoEntryContainer;
      @BindView(R.id.tv_views) TextView repoViews;
      @BindView(R.id.tv_forks) TextView repoForks;
      @BindView(R.id.tv_stars) TextView repoStars;


    RepoItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void setRepoItem(GitHubRepoQuery.Node feedItem, final ListRepositoryView navigator) {


        repositoryTitle.setText(feedItem.name());
        repositoryShortDescription.setText(feedItem.description());
        repositoryLanguage.setText(feedItem.primaryLanguage().name());
        repositoryLanguage.setTextColor(Color.parseColor(feedItem.primaryLanguage().color()));
        repoViews.setText(Integer.toString(feedItem.watchers().totalCount()));
        repoForks.setText(Integer.toString(feedItem.forks().totalCount()));
        repoStars.setText(Integer.toString(feedItem.stargazers().totalCount()));
        repoEntryContainer.setOnClickListener(v -> navigator.showRepoDetail(feedItem));

    }
  }
}
