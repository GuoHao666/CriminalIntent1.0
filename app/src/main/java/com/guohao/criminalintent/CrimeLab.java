package com.guohao.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class CrimeLab {

    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    public CrimeLab(Context mAppContext) {
        this.mAppContext = mAppContext;
        mCrimes=new ArrayList<>();
    }
    //添加Crmie
    public void addCrime(Crime c){
        mCrimes.add(c);
    }

    public static CrimeLab get(Context c) {
        if (sCrimeLab==null){
            sCrimeLab=new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){

        for (Crime c: mCrimes){
            if (c.getId().equals(id)){
                return c;
            }

        }
        return null;
    }
}
