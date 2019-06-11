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
    protected String nazwa;
    protected float cena;
    protected int promocja;
    protected Date data_produkcji;
    protected Date data_przydatnosci;
    protected int ilosc;
    protected String producent;
    protected float cena_brutto;
    
    public Pracownik_dto() {}
    
    public Pracownik_dto(Pracownik_dto o) {
        nazwa = o.getNazwa();
        cena = o.getCena();
        promocja = o.getPromocja();
        data_produkcji = o.getData_produkcji();
        data_przydatnosci = o.getData_przydatnosci();
        ilosc = o.getIlosc();
        producent = o.getProducent();
    }
    
    public Pracownik_dto(String [] dane, Date data, Date dataprzydatnosci){
        nazwa=dane[0];
        cena=Float.parseFloat(dane[1]);
        promocja=Integer.parseInt(dane[2]);
        producent=dane[3];
        ilosc=Integer.parseInt(dane[4]);
        data_produkcji=data;
        data_przydatnosci=dataprzydatnosci;
    }

    public Date getData_przydatnosci() {
        return data_przydatnosci;
    }

    public void setData_przydatnosci(Date data_przydatnosci) {
        this.data_przydatnosci = data_przydatnosci;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public int getPromocja() {
        return promocja;
    }

    public void setPromocja(int promocja) {
        this.promocja = promocja;
    }

    public Date getData_produkcji() {
        return data_produkcji;
    }

    public void setData_produkcji(Date data_produkcji) {
        this.data_produkcji = data_produkcji;
    }

    public float getCena_brutto() {
        return cena_brutto;
    }

    public void setCena_brutto(float cena_brutto) {
        this.cena_brutto = cena_brutto;
    }
}
