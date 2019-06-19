/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa_ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import warstwa_biznesowa.Fasada_warstwy_biznesowej;
import warstwa_biznesowa.dto.Pracownik_dto;
import warstwa_biznesowa.entity.Pracownik1;
import warstwa_integracji_ejb.Pracownik1FacadeLocal;

/**
 *
 * @author Student
 */
@Stateless
public class Fasada_warstwy_biznesowej_ejb implements Fasada_warstwy_biznesowej_ejbRemote {

    @EJB
    private Pracownik1FacadeLocal pracownik1Facade;
    
    Fasada_warstwy_biznesowej fasada = new Fasada_warstwy_biznesowej();
    public void utworz_pracownika(Pracownik_dto pracownik_dto) {
        fasada.utworz_pracownika(pracownik_dto);
    }
    
    public Pracownik_dto dane_pracownika() {
        return fasada.dane_pracownika();
    }
    
    public ArrayList<ArrayList<String>> items() {
        return fasada.items();
    }
    
    public ArrayList<Pracownik_dto> items_() {
        return fasada.items_();
    }
    

    public ArrayList<Pracownik_dto> findRange(int[] range) {
        return fasada.findRange(range);
    }
    
    public int count() {
        return fasada.count();
    }
    
    public void setStan(boolean stan) {
        fasada.setStan(stan);
    }
    
    public boolean isStan() {
        return fasada.isStan();
    }

    public boolean edit(Pracownik_dto o_przed, Pracownik_dto o_update) {
        return fasada.edit(o_przed, o_update);
    }
    
    public void remove(Pracownik_dto p, String imie, String nazwisko, String numerTelefonu, String adres, String miasto, String stanowisko, float pensja, Date dataRozpoczeciaPracy) {
        fasada.remove(p, imie, nazwisko, numerTelefonu, adres, miasto, stanowisko, pensja, dataRozpoczeciaPracy);
    }
    
    public void pobierz() {
        List<Pracownik1> pom = pracownik1Facade.findAll();
        fasada.pracownicy_z_bazy_danych(pom);
    }
    
    public void zapisz() {
        for (Pracownik1 p : fasada.getPracownicy()) {
            Long id = p.getId();
            if (id == null || pracownik1Facade.find(p.getId()) == null) {
                pracownik1Facade.create(p);
            }
        }
    }
    
    @PostConstruct
    public void init() {
        try {
            pobierz();
        } catch (Exception e) {
        }
    }

    public void remove(Pracownik_dto p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
