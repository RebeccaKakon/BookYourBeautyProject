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
        return "Appointment: " + "\n" +
                "client=" + clientName + "\n" + "beautician= " +managerName + "\n" +
                "time=" + startTime + "\n" +
                "date=" + date_app + "\n" +
                "the treatment=" + Tname + "\n" + "AppoNumm="+Aid + "\n" ;
    }
}
