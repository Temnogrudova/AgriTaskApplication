package com.agritask.ekaterinatemnogrudova.agritask.ui;

import com.agritask.ekaterinatemnogrudova.agritask.models.Item;
import com.agritask.ekaterinatemnogrudova.agritask.utils.BaseView;
import java.util.List;

public class ItemsContract {
    public interface View extends BaseView<Presenter> {
        void getItemsSuccess(List<Item> result);
    }

    interface Presenter  {
        void getItems(int offset);
        void unsubscribe();
    }
}
