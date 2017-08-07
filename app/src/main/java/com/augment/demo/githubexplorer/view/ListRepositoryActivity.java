package com.augment.demo.githubexplorer.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.augment.demo.githubexplorer.R;
import com.augment.demo.githubexplorer.adapter.GitHubRepoRecyclerViewAdapter;
import com.augment.demo.githubexplorer.model.DisposableManager;
import com.augment.demo.githubexplorer.presenter.RepositoryPresenter;
import com.augment.demo.githubexplorer.presenter.RepositoryPresenterImpl;
import com.thefinestartist.finestwebview.FinestWebView;


import java.util.List;

import apollo.api.github.api.GitHubRepoQuery;
import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * @author Othman
 * The List Activity UI Part
 */


public class ListRepositoryActivity extends AppCompatActivity implements ListRepositoryView{

    @BindView(R.id.loading_bar)
    ProgressBar  progressBar;
    @BindView(R.id.issue_container)
    View alerteContainer;
    @BindView(R.id.rv_repo_list)
    RecyclerView repoRecyclerView;
    @BindView(R.id.tv_mention_error)
    TextView txtError;


    GitHubRepoRecyclerViewAdapter  repoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_repo);
        ButterKnife.bind(this);

        repoAdapter = new GitHubRepoRecyclerViewAdapter(this);
        repoRecyclerView.setAdapter(repoAdapter);
        repoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RepositoryPresenter presenter = new RepositoryPresenterImpl(this);
        presenter.fetchPublicRepo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DisposableManager.dispose();
    }

    @SuppressWarnings("WrongConstant")
    @Override
    public void showProgress(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void showRepos(List<GitHubRepoQuery.Node> data) {
        repoAdapter.setRepos(data);
    }

    @Override
    public void showRepoDetail(GitHubRepoQuery.Node repo) {
        new FinestWebView.Builder(this).show(repo.url().toString());
    }

    @Override
    public void showAlerte(int visibility, String msg) {
         alerteContainer.setVisibility(visibility);
         txtError.setText(msg);
    }


}
