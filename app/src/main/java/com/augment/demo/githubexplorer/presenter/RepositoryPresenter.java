package com.augment.demo.githubexplorer.presenter;

import java.util.List;

import apollo.api.github.api.GitHubRepoQuery;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author Othman
 * The Presenter Intefcae
 */


public interface RepositoryPresenter {

    void fetchPublicRepo();
    void populateRepo(List<GitHubRepoQuery.Node> data);
    void showAlerte(int visibility, String msg);
    CompositeDisposable disposables();
}
