package com.example.sandra.firebaseproject;

/**
 * Created by 47419119l on 03/02/16.
 */
public class Notas {

    private double lat;
    private double lon;
    private String nota;

    public Notas() {
    }

    public Notas(String nota, double lat, double lon) {
        this.nota = nota;
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
