/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa_ejb;

import java.util.ArrayList;
import javax.ejb.Remote;
import warstwa_biznesowa.dto.Pracownik_dto;

/**
 *
 * @author Student
 */ 
@Remote
public interface Fasada_warstwy_biznesowej_ejbRemote {
    public void utworz_pracownika(Pracownik_dto pracownik_dto);
    public Pracownik_dto dane_pracownika();
    public ArrayList<ArrayList<String>> items();
    public ArrayList<Pracownik_dto> items_();
    public int count();
    public ArrayList<Pracownik_dto> findRange(int[] range);
    public boolean isStan();
    public void setStan(boolean stan);
    public boolean edit(Pracownik_dto o_przed, Pracownik_dto o_update);
    public void remove(Pracownik_dto p, String imie);
    public void zapisz();
    public void pobierz();
}