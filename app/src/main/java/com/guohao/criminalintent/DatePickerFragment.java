package com.guohao.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import java.util.Date;

public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE="com.guohao.criminalintent.date";

    private Date date;

    public static DatePickerFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_DATE,date);

        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }

}
