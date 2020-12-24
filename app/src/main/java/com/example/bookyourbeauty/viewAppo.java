package com.example.bookyourbeauty;

public class viewAppo {
    String startTime;
    String date_app;
    String Tid;
    String Tname;
    String Aid;


    public viewAppo() {}

    @Override
    public String toString() {
        return "Appointment: " + "Aid="+Aid+
                ", startTime=" + startTime + '\'' +
                ", date_app=" + date_app + '\'' +
                ", Tid=" + Tid + '\'' +
                ", Tname=" + Tname + '\'' ;
    }
}
