package com.guohao.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID Id;
    private String Title;
    private Date Date;
    private boolean Solved;

    public Crime() {
        //生成唯一标识符
        Id=UUID.randomUUID();
        Date=new Date();
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
