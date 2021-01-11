package com.example.bookyourbeauty;

public class viewAppo {
    String startTime;
    String date_app;
    String Tid;
    String Tname;
    String Aid;
    String clientName;
    String managerName;


    public viewAppo() {}

    @Override
    public String toString() {
        return  clientName + "\n"  +
                startTime + ":00"+"\n" +
                date_app + "\n" +
                Tname + "\n" + "Appo id: "+Aid +"\n" ;
    }
}
