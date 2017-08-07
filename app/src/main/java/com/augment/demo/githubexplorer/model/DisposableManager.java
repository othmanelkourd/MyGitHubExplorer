package com.augment.demo.githubexplorer.model;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;



/**
 * @author Othman
 * The DisposableManager : This class is to manage the unsubscription/disposable
 * use add() to collect every subscription
 * use dispose to free subscriptions when the work is done
 */

public class DisposableManager {

    private static CompositeDisposable compositeDisposable;

    public static void add(Disposable disposable) {
        getCompositeDisposable().add(disposable);
    }

    public static void dispose() {
        getCompositeDisposable().dispose();
    }

    private static CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    private DisposableManager() {}
}
