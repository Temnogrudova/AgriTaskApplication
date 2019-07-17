package com.agritask.ekaterinatemnogrudova.agritask.ui;

import android.util.Log;

import com.agritask.ekaterinatemnogrudova.agritask.agriTaskApi.ItemsService;
import com.agritask.ekaterinatemnogrudova.agritask.models.Item;
import com.agritask.ekaterinatemnogrudova.agritask.utils.IScheduler;
import java.util.List;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import static com.agritask.ekaterinatemnogrudova.agritask.utils.Constants.REQUEST_LIMIT;

public class ItemsPresenter implements ItemsContract.Presenter {
    private ItemsContract.View mView;
    private Disposable mDisposable;
    private IScheduler mScheduler;
    private ItemsService mItemsService;

    public ItemsPresenter(ItemsContract.View view, ItemsService itemsService, IScheduler scheduler) {
        mView = view;
        mView.setPresenter(this);
        mScheduler = scheduler;
        mItemsService = itemsService;
    }

    @Override
    public void unsubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void getItems(int offset) {
        mDisposable = mItemsService.getItems(offset, REQUEST_LIMIT)
                    .subscribeOn(mScheduler.io())
                    .observeOn(mScheduler.ui()).subscribeWith(new DisposableObserver<List<Item>>() {
            @Override
            public void onNext(List<Item> response) {
                mView.getItemsSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("NETWORK ERROR", "getItems() loading error");
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
