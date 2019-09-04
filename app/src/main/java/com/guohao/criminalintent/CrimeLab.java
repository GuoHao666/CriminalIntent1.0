package com.guohao.criminalintent;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class CrimeLab {

    private static final String TAG="CrimeLab";
    private static final String FILENAME="crimes.json";

    private ArrayList<Crime> mCrimes;
    private CrimeIntentJSONSerializer mSerializer;


    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    public CrimeLab(Context mAppContext) {
        this.mAppContext = mAppContext;
        mSerializer=new CrimeIntentJSONSerializer(mAppContext,FILENAME);

        try {
            mCrimes=mSerializer.loadCrimes();
        } catch (IOException e) {
            mCrimes=new ArrayList<>();
            Log.e(TAG,"Error loading crimes:",e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //向沙盒中存储json对象
    public boolean saveCrimes(){
        try {
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG,"crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes:",e );
            return false;
        }
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
