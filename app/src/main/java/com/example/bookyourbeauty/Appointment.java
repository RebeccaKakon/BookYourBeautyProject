package com.example.bookyourbeauty;

public class Appointment {
    private String idAppo;
    public String idTreatment;
    private String idClient;
    private String idManager;
    private String date_app;
    private String startTime;
    private String endTime;
//    static int countIdAppointment=0;

    public Appointment(){
    }

//    public Appointment(String choosenDate, String choosenStartTime, String choosenEndTime, String manaherId,String clientId, String IdTreatment) {
//        this.date_app=choosenDate;
//        this.startTime=choosenStartTime;
//        this.endTime=choosenEndTime;
////        this.idAppo=countIdAppointment;
////        this.countIdAppointment++;
//        this.idManager= manaherId;
//        this.idClient=clientId;
//       this.IdTreatment= IdTreatment;
//    }


    public void setIdOfTreatment(String IdT) {
        this.idTreatment = IdT;
    }

    public String  geIdOfTreatment() {
        return idTreatment;
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