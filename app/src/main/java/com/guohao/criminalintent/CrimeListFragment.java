package com.guohao.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.util.Log;
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

        CrimeAdapter adapter=new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime c=((CrimeAdapter)getListAdapter()).getItem(position);
        Intent i=new Intent(getActivity(),CrimeActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getId());
        startActivity(i);
    }
}
