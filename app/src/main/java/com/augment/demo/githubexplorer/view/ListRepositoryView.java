package com.augment.demo.githubexplorer.view;


import java.util.List;
import apollo.api.github.api.GitHubRepoQuery;


/**
 * @author Othman
 * The View Intefcae
 */

public interface ListRepositoryView {

    void showProgress(int visibility);
    void showRepos(List<GitHubRepoQuery.Node> data);
    void showRepoDetail(GitHubRepoQuery.Node repo);
    void showAlerte(int visibility, String msg);
}
