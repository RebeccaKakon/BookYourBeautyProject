package com.example.bookyourbeauty;

public class Info {
    private String information;
    private String MId;
//    String idManager;


    public Info(){}

    public String getMId() {
        return MId;
    }

    public void setMId(String MId) {
        this.MId = MId;
    }

    public String getinformation() {
        return information;
    }

    public void setinformation(String info) {
        this.information = info;
    }


    @Override
    public String toString() {
        return MId+ " :"+ information ;
    }
}
