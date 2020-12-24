package com.example.bookyourbeauty;

public class Info {
    private String information;
//    String idManager;


    public Info(){}

    public String getinformation() {
        return information;
    }

    public void setinformation(String info) {
        this.information = info;
    }

    @Override
    public String toString() {
        return "information: " + information ;
    }
}
