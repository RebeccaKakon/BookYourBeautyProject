package com.example.bookyourbeauty;

import java.io.Serializable;

public class Client implements Serializable {

    private String email;//id
    private String first_name;
    private String last_name;
    private String phone;
    private String date_birth;
    //    boolean female;
//    boolean male;
    private String password;
    //private Button save;

    public Client(){

    }

    public Client (String first_name, String email, String password,String last_name,String date_birth,String phone){
//    this.id_db=id;
        this.first_name=first_name;
        this.email=email;
        this.last_name=last_name;
        this.phone= phone;
        this.date_birth=date_birth;
//    this.female= female;
//    this.male=male;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", date_birth='" + date_birth + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}