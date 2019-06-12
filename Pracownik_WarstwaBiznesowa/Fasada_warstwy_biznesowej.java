package warstwa_biznesowa;

import java.util.ArrayList;
import java.util.List;
import warstwa_biznesowa.dto.Pracownik_dto;
import warstwa_biznesowa.entity.Pracownik1;

public class Fasada_warstwy_biznesowej {

    static long klucz = 0;
    private ArrayList<Pracownik1> pracownicy = new ArrayList();
    boolean stan = false;

    public ArrayList<Pracownik1> getPracownicy() {
        return pracownicy;
    }

    public void setPracownicy(ArrayList<Pracownik1> pracownicy) {
        this.pracownicy = pracownicy;
    }
    public void utworz_pracownika(Pracownik_dto pracownik_dto) {
        Pracownik1 pracownik = wykonaj_pracownik(pracownik_dto);

        dodaj_pracownik(pracownik);
    }
    
    
    public Pracownik_dto pracownik_transfer(Pracownik1 pracownik) {
        Pracownik_dto pom = new Pracownik_dto();
        pom.setId(pracownik.getId_());
        pom.setNazwa(pracownik.getNazwa());
        pom.setCena(pracownik.getCena());
        pom.setPromocja(pracownik.getPromocja());
        pom.setData_produkcji(pracownik.getData_produkcji());
        pom.setCena_brutto(pracownik.cena_brutto());
        pom.setData_przydatnosci(pracownik.getData_przydatnosci());
        pom.setProducent(pracownik.getProducent());
        pom.setIlosc(pracownik.getIlosc());
        return pom;
    }
    
    public Pracownik_dto dane_pracownika() {
        if (stan) {
            Pracownik1 pracownik = pracownicy.get(pracownicy.size() -1);
            return pracownik_transfer(pracownik);
        }
        return null;
    }

    protected void dodaj_pracownik(Pracownik1 pracownik) {
        if (!pracownicy.contains(pracownik)) {
            pracownicy.add(pracownik);
            stan = true;
        } else {
            stan = false;
        }
    }

    public ArrayList<Pracownik_dto> items_() {
        ArrayList<Pracownik_dto> dane = new ArrayList();
        for (Pracownik1 pracownik : pracownicy)
            dane.add(pracownik_transfer(pracownik));
        return dane;
    }

    public ArrayList<ArrayList<String>> items() {
        ArrayList<ArrayList<String>> dane = new ArrayList();
        for (Pracownik1 p : pracownicy) {
            ArrayList<String> wiersz = new ArrayList();
            wiersz.add(p.getId_().toString());
            wiersz.add(p.getNazwa());
            wiersz.add("" + p.getCena());
            wiersz.add("" + p.getPromocja());
            wiersz.add(p.getData_produkcji().toString());
            wiersz.add("" + p.cena_brutto());
            wiersz.add(p.getData_przydatnosci().toString());
            wiersz.add("" + p.getProducent());
            wiersz.add("" + p.getIlosc());
            dane.add(wiersz);
        }
        return dane;
    }
    
    public boolean isStan() {
        return stan;
    }
    
    public void setStan(boolean stan) {
        this.stan = stan;
    }
    
    public int count() {
        return pracownicy.size();
    }
    
    public ArrayList<Pracownik_dto> findRange(int[] range) {
        ArrayList<Pracownik_dto> pom = new ArrayList();
        if (getPracownicy().isEmpty()) {
            stan = false;
            return pom;
        }
        for (int i = range[0]; i < range[1]; i++) {
            pom.add(pracownik_transfer(getPracownicy().get(i)));
        }
        return pom;
    }

    int istnieje_pracownik(Pracownik_dto pdto) {
        Pracownik1 pom1 = this.wykonaj_pracownik(pdto);
        return getPracownicy().indexOf(pom1);
    }
    
    public boolean edit(Pracownik_dto o_przed, Pracownik_dto o_update) {
        int idx1, idx2;
        stan = true;
        idx1=this.istnieje_pracownik(o_przed);
        if(idx1==-1) //taki pracownik do edycji nie istnieje
        {
            return false;}
        idx2=this.istnieje_pracownik(o_update);
        if(idx2!=-1) //nie mozna modyfikowac, bo już taki pracownik istnieje
        {
            return false;}
        Pracownik1 p = getPracownicy().get(idx1);
        p.setCena(o_update.getCena());
        p.setData_produkcji(o_update.getData_produkcji());
        p.setPromocja(o_update.getPromocja());
        return true;
    }
    void max_klucz() {
        long max = 0;
        for (Pracownik1 p : pracownicy)
            if (p.getId() > max) max = p.getId();
        klucz = max + 1;
    }
    
    Pracownik1 wykonaj_pracownik(Pracownik_dto pracownik_dto) {
        Pracownik1 pracownik = new Pracownik1();
        max_klucz();
        pracownik.setId(new Long(klucz));
        pracownik.setNazwa(pracownik_dto.getNazwa());
        pracownik.setCena(pracownik_dto.getCena());
        pracownik.setPromocja(pracownik_dto.getPromocja());
        pracownik.setData_produkcji(pracownik_dto.getData_produkcji());
        pracownik.setData_przydatnosci(pracownik_dto.getData_przydatnosci());
        pracownik.setProducent(pracownik_dto.getProducent());
        pracownik.setIlosc(pracownik_dto.getIlosc());
        return pracownik;
    }

    public void remove(Pracownik_dto p) {
        Pracownik1 pracownik = wykonaj_pracownik(p);
        getPracownicy().remove(pracownik);
    }

    public void pracownicy_z_bazy_danych (List<Pracownik1> pracownicy_)
    {
        pracownicy.clear();
        pracownicy.addAll(pracownicy_);
    }
}
