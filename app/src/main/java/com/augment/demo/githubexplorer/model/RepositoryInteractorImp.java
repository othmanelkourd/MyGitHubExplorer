package com.augment.demo.githubexplorer.model;

import android.view.View;

import com.apollographql.apollo.ApolloQueryWatcher;
import com.apollographql.apollo.api.Error;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.augment.demo.githubexplorer.presenter.RepositoryPresenter;

import apollo.api.github.api.GitHubRepoQuery;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Othman
 * The Interactor Implementation
 * fetchPublicRepo : fetching the repositories information
 * and using RxJava2 to subscribe to feeds by using Rx2Apollo
 */

public class RepositoryInteractorImp implements RepositoryInteractor{

    private final String TAG = RepositoryInteractorImp.class.getName();
    RepositoryPresenter                         mPresenter;
    ApolloQueryWatcher<GitHubRepoQuery.Data>    mWatcher;
    Observable<Response<GitHubRepoQuery.Data>>  mObservable;

    Observer<Response<GitHubRepoQuery.Data>> mObserver = new Observer<Response<GitHubRepoQuery.Data>>() {
        @Override
        public void onSubscribe(Disposable d) {
            DisposableManager.add(d);
        }
        @Override
        public void onNext(Response<GitHubRepoQuery.Data> dataResponse) {
            if(!dataResponse.hasErrors())
                mPresenter.populateRepo(dataResponse.data().user().repositories().nodes());
            else{
                String message = "";
                for (Error error:dataResponse.errors()
                     ) {
                    message = message + error.message();
                }
                mPresenter.showAlerte(View.VISIBLE, message);
            }
        }

        @Override
        public void onError(Throwable e) {
            mPresenter.showAlerte(View.VISIBLE, GitHubService.connection_error);
        }

        @Override
        public void onComplete() {

        }
    };

    public RepositoryInteractorImp(RepositoryPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void fetchPublicRepo() {
        mWatcher = GitHubService.createApolloClient()
                .query(GitHubRepoQuery.builder().number_of_repos(GitHubService.REPO_NUMBER).user_name(GitHubService.USER_NAME).build())
                .watcher();
        mObservable = Rx2Apollo.from(mWatcher);
        mObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }

}
