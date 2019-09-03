package com.guohao.criminalintent;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes;

    private final String TAG="CrimeListFragment";

    private boolean mSubTitleVisiable;




    private class CrimeAdapter extends ArrayAdapter<Crime>{
        public CrimeAdapter (ArrayList<Crime> crimes){
            super(getActivity(),0,crimes);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime,null);
                Crime c=getItem(position);
                //从CrimeList中获取Crime对象并失配到CrimeAdapter中
                TextView titleTextView=(TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
                titleTextView.setText(c.getTitle());
                TextView dateTextView=(TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
                dateTextView.setText(c.getDate().toString());
                CheckBox solvedCheckBox=(CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
                solvedCheckBox.setChecked(c.isSolved());
            }
            return convertView;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes=CrimeLab.get(getActivity()).getCrimes();

        setHasOptionsMenu(true);


        CrimeAdapter adapter=new CrimeAdapter(mCrimes);
        setListAdapter(adapter);

        setRetainInstance(true);
        mSubTitleVisiable=false;
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v=super.onCreateView(inflater,parent,savedInstanceState);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            if (mSubTitleVisiable){
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime c=((CrimeAdapter)getListAdapter()).getItem(position);
        Intent i=new Intent(getActivity(),CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getId());
        startActivity(i);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);

        MenuItem showSubTitle=menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubTitleVisiable&&showSubTitle!=null){
            showSubTitle.setTitle(R.string.hide_subtitle);
        }
    }
    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_crime:
                Crime crime=new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent i=new Intent(getActivity(),CrimePagerActivity.class);
                i.putExtra(CrimeFragment.EXTRA_CRIME_ID,crime.getId());
                startActivityForResult(i,0);
                return true;
            case R.id.menu_item_show_subtitle:
                if (getActivity().getActionBar().getSubtitle()==null){
                    getActivity().getActionBar().setSubtitle(R.string.subtitle);
                    mSubTitleVisiable=true;
                    item.setTitle(R.string.hide_subtitle);
                }else {
                    getActivity().getActionBar().setSubtitle(null);
                    mSubTitleVisiable=false;
                    item.setTitle(R.string.show_subtitle);
                }
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
