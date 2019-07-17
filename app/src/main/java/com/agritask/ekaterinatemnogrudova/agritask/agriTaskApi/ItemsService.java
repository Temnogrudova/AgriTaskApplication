package com.agritask.ekaterinatemnogrudova.agritask.agriTaskApi;

import com.agritask.ekaterinatemnogrudova.agritask.R;
import com.agritask.ekaterinatemnogrudova.agritask.models.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;

public class ItemsService {

    public Observable<List<Item>> getItems(int offset, int limit) {
        return Observable.just(simulateGetItemsFromServer(offset, limit)).delay(1, TimeUnit.SECONDS);
    }

    private List<Item> simulateGetItemsFromServer(int offset, int limit){
        ArrayList<Item> arrayList = new ArrayList<>();
        for (int i = offset; i < offset + limit; i++)
        {
            Item item = new Item();
            item.setId(i);
            item.setResId(R.drawable.ic_assignment_image);
            arrayList.add(item);
        }
        return arrayList;
    }
}