package com.example.myapplication.Adapter;

public class Grias {
    private String nombresgria;
    private String emailgria;

    public Grias(String nombresgria,String emailgria){
        this.nombresgria = nombresgria;
        this.emailgria = emailgria;
    }

    public String getNombresgria() {
        return nombresgria;
    }

    public void setNombresgria(String nombresgria) {
        this.nombresgria = nombresgria;
    }

    public String getEmailgria() {
        return emailgria;
    }

    public void setEmailgria(String emailgria) {
        this.emailgria = emailgria;
    }
}
