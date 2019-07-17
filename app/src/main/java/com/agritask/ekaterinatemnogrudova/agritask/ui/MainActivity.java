package com.agritask.ekaterinatemnogrudova.agritask.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.agritask.ekaterinatemnogrudova.agritask.R;
import com.agritask.ekaterinatemnogrudova.agritask.databinding.ActivityMainBinding;
import static com.agritask.ekaterinatemnogrudova.agritask.utils.Constants.FRAGMENT_ITEMS;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinder.toolBar.setTitle(getString(R.string.app_name));
        if (savedInstanceState == null) {
            ItemsListFragment itemsListFragment = ItemsListFragment.newInstance();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, itemsListFragment, FRAGMENT_ITEMS);
            fragmentTransaction.addToBackStack(FRAGMENT_ITEMS);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
