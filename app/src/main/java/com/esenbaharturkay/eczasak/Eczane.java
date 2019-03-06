package com.esenbaharturkay.eczasak;

public class Eczane {
    private String adi;
    private String adress;
    private String telefon;

    public void setAdi(String adi) {
        this.adi=adi;
    }
    public void setAdress(String address) {
        this.adress=address;
    }
    public void setTelefon(String telefon) {
        this.telefon=telefon;
    }

    public String getAdi() {
        return this.adi;
    }
    public String getAdress() {
        return this.adress;
    }
    public String getTelefon() {
        return this.telefon;
    }

}
