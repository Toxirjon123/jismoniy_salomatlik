package com.example.jismoniysalomatlik.model;

public class AnketaModel {

    String famil;
    String Imya;
    String otchestvo;
    String datarojdeniya;
    String oblast;
   String gorod;
    String rayon;
    String kucha;
    int dom;
    String kvartira;
    int phone;
    String pol;

    public AnketaModel(String famil, String imya, String otchestvo, String datarojdeniya, String oblast, String gorod, String rayon, String kucha, int dom, String kvartira, int phone, String pol) {
        this.famil = famil;
        Imya = imya;
        this.otchestvo = otchestvo;
        this.datarojdeniya = datarojdeniya;
        this.oblast = oblast;
        this.gorod = gorod;
        this.rayon = rayon;
        this.kucha = kucha;
        this.dom = dom;
        this.kvartira = kvartira;
        this.phone = phone;
        this.pol = pol;
    }

    public String getFamil() {
        return famil;
    }

    public void setFamil(String famil) {
        this.famil = famil;
    }

    public String getImya() {
        return Imya;
    }

    public void setImya(String imya) {
        Imya = imya;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getDatarojdeniya() {
        return datarojdeniya;
    }

    public void setDatarojdeniya(String datarojdeniya) {
        this.datarojdeniya = datarojdeniya;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getGorod() {
        return gorod;
    }

    public void setGorod(String gorod) {
        this.gorod = gorod;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getKucha() {
        return kucha;
    }

    public void setKucha(String kucha) {
        this.kucha = kucha;
    }

    public int getDom() {
        return dom;
    }

    public void setDom(int dom) {
        this.dom = dom;
    }

    public String getKvartira() {
        return kvartira;
    }

    public void setKvartira(String kvartira) {
        this.kvartira = kvartira;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }
}
