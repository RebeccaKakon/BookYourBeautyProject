package com.example.bookyourbeauty;

public class Appointment {
    private String idAppo;
    private String idClient;
    private String  idManager;
    private String date_app;
    private String startTime;
    private String endTime;
    private String IdTreatment;
    static int countIdAppointment=0;

    public Appointment(){
    }

    public Appointment(String choosenDate, String choosenStartTime, String choosenEndTime, String manaherId,String clientId) {
        this.date_app=choosenDate;
        this.startTime=choosenStartTime;
        this.endTime=choosenEndTime;
//        this.idAppo=countIdAppointment;
        this.countIdAppointment++;
        this.idManager= manaherId;
        this.idClient=clientId;
    }



//    public Appointment(int idApo,String idClient, String idManager,int idTreatment,String date,String startTime,String endTime){
//        this.idAppo=idApo;
//        this.idClient=idClient;
//        this.idManager=idManager;
//        this.idTreatment=idTreatment;
//        this.date=date;
//        this.startTime=startTime;
//        this.endTime=endTime;
//    }
public void setIdTreatment(String setIdTreatment) {
    this.IdTreatment = setIdTreatment;
}
    public String  geIdTreatment() {
        return IdTreatment;
    }

    public void setdate_app(String date_app) {
        this.date_app = date_app;
    }
    public String getdate_app() {
        return date_app;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String  getIdAppo() {
        return idAppo;
    }
    public void setIdAppo(String id) {
        this.idAppo = id;
//        countIdAppointment++;
    }

    public String getIdClient() {
        return idClient;
    }
    public void setIdClient(String currId) {
        this.idClient = currId;
    }

    public String getIdManager() {
        return idManager;
    }
    public void setIdManager(String currId) {
        this.idManager = currId;
    }

//    public int getidAppointment() {
//        return idAppointment;
//    }
//
//    public void setidAppointment(int currId) {
//        this.idAppointment = currId;
//    }

    @Override
    public String toString() {
        return "Appointment{" +
                "start time of the treatment='" + startTime + '\'' +
                "end time of the treatment='" + endTime + '\'' +
                ", Appointment id='" + idAppo + '\'' +
                ", Client id='" + idClient + '\'' +
                ", Manager id='" + idManager + '\'' +
                ", Treatment id='"  ;
    }


}