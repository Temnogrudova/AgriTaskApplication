package com.agritask.ekaterinatemnogrudova.agritask.ui;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.agritask.ekaterinatemnogrudova.agritask.R;
import com.agritask.ekaterinatemnogrudova.agritask.agriTaskApi.ItemsService;
import com.agritask.ekaterinatemnogrudova.agritask.databinding.FragmentListItemsBinding;
import com.agritask.ekaterinatemnogrudova.agritask.models.Item;
import com.agritask.ekaterinatemnogrudova.agritask.utils.SchedulerProvider;
import java.util.ArrayList;
import java.util.List;
import static com.agritask.ekaterinatemnogrudova.agritask.utils.Constants.REQUEST_LIMIT;

public class ItemsListFragment extends Fragment  implements ItemsContract.View {
    protected FragmentListItemsBinding mBinder;
    private ItemsContract.Presenter mPresenter;
    private List<Item> mItems = new ArrayList<>();
    private ItemsListAdapter mAdapter;
    private boolean isLoading = false;
    private int mOffset = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    public static ItemsListFragment newInstance() {
        return new ItemsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_list_items, container, false);
        mPresenter = new ItemsPresenter(this, new ItemsService(), new SchedulerProvider());
        initItemList();
        if (savedInstanceState ==null) {
            getItems();
        }
        return mBinder.getRoot();
    }

    private void initItemList() {
        mBinder.itemList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ItemsListAdapter(mItems);
        mBinder.itemList.setAdapter(mAdapter);
        mBinder.itemList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == mItems.size() - 1) {
                        mOffset += REQUEST_LIMIT;
                        getItems();
                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
    }

    @Override
    public void setPresenter(ItemsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getItemsSuccess(List<Item> result) {
        isLoading = false;
        mBinder.networkProgress.setVisibility(View.GONE);
        mItems.addAll(result);
        mAdapter.notifyDataSetChanged();
    }

    private void getItems() {
        mPresenter.getItems(mOffset);
        mBinder.networkProgress.setVisibility(View.VISIBLE);
    }
}
