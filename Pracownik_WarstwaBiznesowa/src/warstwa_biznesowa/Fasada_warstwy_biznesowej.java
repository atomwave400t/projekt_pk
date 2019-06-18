package warstwa_biznesowa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
        pom.setImie(pracownik.getImie());
        pom.setNazwisko(pracownik.getNazwisko());
        pom.setNumerTelefonu(pracownik.getNumerTelefonu());
        pom.setAdres(pracownik.getAdres());
        pom.setMiasto(pracownik.getMiasto());
        pom.setStanowisko(pracownik.getStanowisko());
        pom.setPensja(pracownik.getPensja());
        pom.setDataRozpoczeciaPracy(pracownik.getDataRozpoczeciaPracy());
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
            wiersz.add(p.getImie());
            wiersz.add(p.getNazwisko());
            wiersz.add(p.getNumerTelefonu());
            wiersz.add(p.getAdres());
            wiersz.add(p.getMiasto());
            wiersz.add(p.getStanowisko());
            wiersz.add("" + p.getPensja());
            wiersz.add(p.getDataRozpoczeciaPracy().toString());
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
            return false;
        }
        Pracownik1 p = getPracownicy().get(idx1);
        p.setImie(o_update.getImie());
        p.setNazwisko(o_update.getNazwisko());
        p.setNumerTelefonu(o_update.getNumerTelefonu());
        p.setAdres(o_update.getAdres());
        p.setMiasto(o_update.getMiasto());
        p.setStanowisko(o_update.getStanowisko());
        p.setPensja(o_update.getPensja());
        p.setDataRozpoczeciaPracy(o_update.getDataRozpoczeciaPracy());
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
        pracownik.setImie(pracownik_dto.getImie());
        pracownik.setNazwisko(pracownik_dto.getNazwisko());
        pracownik.setNumerTelefonu(pracownik_dto.getNumerTelefonu());
        pracownik.setAdres(pracownik_dto.getAdres());
        pracownik.setMiasto(pracownik_dto.getMiasto());
        pracownik.setStanowisko(pracownik_dto.getStanowisko());
        pracownik.setPensja(pracownik_dto.getPensja());
        pracownik.setDataRozpoczeciaPracy(pracownik_dto.getDataRozpoczeciaPracy());
        return pracownik;
    }

    public void removeBy(Pracownik_dto p) {
        Pracownik1 pracownik = wykonaj_pracownik(p);
        getPracownicy().remove(pracownik);
    }
    ArrayList<Pracownik1> pracownicy_filtered = new ArrayList();

    public ArrayList<Pracownik1> getPracownicy_filtered() {
        return pracownicy_filtered;
    }

    public void setPracownicy_filtered(ArrayList<Pracownik1> pracownicy_filtered) {
        this.pracownicy_filtered = pracownicy_filtered;
    }
    public void remove(Pracownik_dto p, 
            String imie, 
            String nazwisko, 
            String numerTelefonu, 
            String adres, 
            String miasto, 
            String stanowisko,
            String pensja,
            Date dataRozpoczeciaPracy) {
        //usuwanie po imieniu
        for (int i = 0; i < getPracownicy().size(); i++) {
            if(imie.equals(getPracownicy().get(i).getImie())) {
                getPracownicy().remove(getPracownicy().get(i));
                i=0;
            }
        }
        //usuwanie po nazwisku
        for (int i = 0; i < getPracownicy().size(); i++) {
            if(nazwisko.equals(getPracownicy().get(i).getNazwisko())) {
                getPracownicy().remove(getPracownicy().get(i));
                i=0;
            }
        }
        //usuwanie po numerze telefonu
        for (int i = 0; i < getPracownicy().size(); i++) {
            if(numerTelefonu.equals(getPracownicy().get(i).getNumerTelefonu())) {
                getPracownicy().remove(getPracownicy().get(i));
                i=0;
            }
        }
        //usuwanie po adresie
        for (int i = 0; i < getPracownicy().size(); i++) {
            if(adres.equals(getPracownicy().get(i).getAdres())) {
                getPracownicy().remove(getPracownicy().get(i));
                i=0;
            }
        }
        //usuwanie po miescie
        for (int i = 0; i < getPracownicy().size(); i++) {
            if(miasto.equals(getPracownicy().get(i).getMiasto())) {
                getPracownicy().remove(getPracownicy().get(i));
                i=0;
            }
        }
        //usuwanie po stanowisku
        for (int i = 0; i < getPracownicy().size(); i++) {
            if(stanowisko.equals(getPracownicy().get(i).getStanowisko())) {
                getPracownicy().remove(getPracownicy().get(i));
                i=0;
            }
        }
        //usuwanie po pensji
//        for (int i = 0; i < getPracownicy().size(); i++) {
////            if(Float.floatToIntBits(pensja) == Float.floatToIntBits(getPracownicy().get(i).getPensja())) {
//            if(pensja.equals(""+getPracownicy().get(i).getPensja())){
//                getPracownicy().remove(getPracownicy().get(i));
//                i=0;
//            }
//        }
        //usuwanie po dacie
        for (int i = 0; i < getPracownicy().size(); i++) {
//            if(dataRozpoczeciaPracy.equals(getPracownicy().get(i).getDataRozpoczeciaPracy())) {
            if(dataRozpoczeciaPracy.compareTo(getPracownicy().get(i).getDataRozpoczeciaPracy()) == 0) {
                getPracownicy().remove(getPracownicy().get(i));
                i=0;
            }
        }
//        //usuwanie po nazwisku
//        for (int i = 0; i < getPracownicy().size(); i++) {
//            if(nazwisko.equals(getPracownicy().get(i).getNazwisko())) {
//                getPracownicy().remove(getPracownicy().get(i));
//                i=0;
//            }
//        }
//        //usuwanie po numerze telefonu
//        for (int i = 0; i < getPracownicy().size(); i++) {
//            if(numerTelefonu.equals(getPracownicy().get(i).getNumerTelefonu())) {
//                getPracownicy().remove(getPracownicy().get(i));
//                i=0;
//            }
//        }
//        //usuwanie po adresie
//        for (int i = 0; i < getPracownicy().size(); i++) {
//            if(adres.equals(getPracownicy().get(i).getAdres())) {
//                getPracownicy().remove(getPracownicy().get(i));
//                i=0;
//            }
//        }
//        //usuwanie po miescie
//        for (int i = 0; i < getPracownicy().size(); i++) {
//            if(miasto.equals(getPracownicy().get(i).getMiasto())) {
//                getPracownicy().remove(getPracownicy().get(i));
//                i=0;
//            }
//        }
//        //usuwanie po stanowisku
//        for (int i = 0; i < getPracownicy().size(); i++) {
//            if(stanowisko.equals(getPracownicy().get(i).getStanowisko())) {
//                getPracownicy().remove(getPracownicy().get(i));
//                i=0;
//            }
//        }
//        //usuwanie po pensji
//        for (int i = 0; i < getPracownicy().size(); i++) {
//            if(Float.floatToIntBits(pensja) == Float.floatToIntBits(getPracownicy().get(i).getPensja())) {
//                getPracownicy().remove(getPracownicy().get(i));
//                i=0;
//            }
//        }
        //usuwanie po dacie
//        for (int i = 0; i < getPracownicy().size(); i++) {
//            if(dataRozpoczeciaPracy.equals(getPracownicy().get(i).getDataRozpoczeciaPracy())) {
//                getPracownicy().remove(getPracownicy().get(i));
//                i=0;
//            }
//        }
        
        
    }

    public void pracownicy_z_bazy_danych (List<Pracownik1> pracownicy_)
    {
        pracownicy.clear();
        pracownicy.addAll(pracownicy_);
    }
}
