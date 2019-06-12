/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Student
 */
public class Pracownik_dto implements Serializable{
    protected long id;
    protected String imie;
    protected String nazwisko;
    protected String numerTelefonu;
    protected String adres;
    protected String miasto;
    protected String stanowisko;
    protected float pensja;
    protected Date dataRozpoczeciaPracy;
    
    public Pracownik_dto() {}
    
    public Pracownik_dto(Pracownik_dto o) {
        imie = o.getImie();
        nazwisko = o.getNazwisko();
        numerTelefonu = o.getNumerTelefonu();
        adres = o.getAdres();
        miasto = o.getMiasto();
        stanowisko = o.getStanowisko();
        pensja = o.getPensja();
        dataRozpoczeciaPracy = o.getDataRozpoczeciaPracy();
    }
    
//        pom.setId(pracownik.getId_());
//        pom.setImie(pracownik.getImie());
//        pom.setNazwisko(pracownik.getNazwisko());
//        pom.setNumerTelefonu(pracownik.getNumerTelefonu());
//        pom.setAdres(pracownik.getAdres());
//        pom.setMiasto(pracownik.getMiasto());
//        pom.setStanowisko(pracownik.getStanowisko());
//        pom.setPensja(pracownik.getPensja());
//        pom.setDataRozpoczeciaPracy(pracownik.getDataRozpoczeciaPracy());
    
    public Pracownik_dto(String [] dane, Date dataRozpoczecia){
        imie=dane[0];
        nazwisko=dane[1];
        numerTelefonu=dane[2];
        adres=dane[3];
        miasto=dane[4];
        stanowisko=dane[5];
        pensja=Float.parseFloat(dane[6]);
        dataRozpoczeciaPracy=dataRozpoczecia;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public float getPensja() {
        return pensja;
    }

    public void setPensja(float pensja) {
        this.pensja = pensja;
    }

    public Date getDataRozpoczeciaPracy() {
        return dataRozpoczeciaPracy;
    }

    public void setDataRozpoczeciaPracy(Date dataRozpoczeciaPracy) {
        this.dataRozpoczeciaPracy = dataRozpoczeciaPracy;
    }

    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
