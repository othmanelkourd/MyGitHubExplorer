package com.augment.demo.githubexplorer.presenter;


import android.view.View;
import com.augment.demo.githubexplorer.model.RepositoryInteractor;
import com.augment.demo.githubexplorer.model.RepositoryInteractorImp;
import com.augment.demo.githubexplorer.view.ListRepositoryView;
import java.util.List;
import apollo.api.github.api.GitHubRepoQuery;
import io.reactivex.disposables.CompositeDisposable;


/**
 * @author Othman
 * The Presenter Implementation
 * The bridge between the view and the Model
 */

public class RepositoryPresenterImpl implements RepositoryPresenter{

    RepositoryInteractor mInteractor;
    ListRepositoryView   mView;
    CompositeDisposable  mDisposables;


    public RepositoryPresenterImpl(ListRepositoryView view) {
        this.mInteractor = new RepositoryInteractorImp(this);
        mView = view;
        mDisposables = new CompositeDisposable();

    }


    @Override
    public void fetchPublicRepo() {
        mView.showProgress(View.VISIBLE);
        mView.showAlerte(View.GONE,"");
        mInteractor.fetchPublicRepo();

    }

    @Override
    public void populateRepo(List<GitHubRepoQuery.Node> data) {
        mView.showProgress(View.GONE);
        mView.showRepos(data);

    }

    @Override
    public void showAlerte(int visibility, String msg) {
        mView.showProgress(View.GONE);
        mView.showAlerte(visibility, msg);
    }

    @Override
    public CompositeDisposable disposables() {
        return mDisposables;
    }
}
