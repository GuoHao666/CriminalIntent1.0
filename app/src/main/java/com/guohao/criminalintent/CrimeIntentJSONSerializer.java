package com.guohao.criminalintent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class CrimeIntentJSONSerializer {
    private Context mContext;
    private String mFilename;

    public CrimeIntentJSONSerializer(Context c, String f) {
        this.mContext = c;
        this.mFilename = f;
    }

    public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException{
        JSONArray array=new JSONArray();
        for (Crime c: crimes){
            array.put(c);
        }
        Writer writer=null;
        try {
            OutputStream out=mContext.openFileOutput(mFilename,Context.MODE_PRIVATE);
            writer=new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer!=null) writer.close();
        }
    }
}
