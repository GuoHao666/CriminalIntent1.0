package com.guohao.criminalintent;


import android.app.ActionBar;
import android.support.v4.app.Fragment;


public class CrimeListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
