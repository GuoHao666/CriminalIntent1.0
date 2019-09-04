package com.guohao.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private static final String JSON_ID="id";
    private static final String JSON_TITLE="title";
    private static final String JSON_SOLVED="solved";
    private static final String JSON_DATE="date";


    private UUID Id;
    private String Title;
    private Date Date;
    private boolean Solved;

    public Crime() {
        //生成唯一标识符
        Id=UUID.randomUUID();
        Date=new Date();
    }
    //向Crime中写入json类型的数据
    public Crime(JSONObject json)throws JSONException{
        Id=UUID.fromString(json.getString(JSON_ID));
        if (json.has(JSON_TITLE)) Title=json.getString(JSON_TITLE);
        Solved=json.getBoolean(JSON_SOLVED);
        Date=new Date(json.getLong(JSON_DATE));
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json=new JSONObject();
        json.put(JSON_ID,Id.toString());
        json.put(JSON_TITLE,Title);
        json.put(JSON_SOLVED,Solved);
        json.put(JSON_DATE,Date.getTime());
        return json;
    }

    @Override
    public String toString(){
        return Title;
    }

    public UUID getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String mTitle) {
        this.Title = mTitle;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Data) {
        this.Date = Data;
    }

    public boolean isSolved() {
        return Solved;
    }

    public void setSolved(boolean Solved) {
        this.Solved = Solved;
    }
}
