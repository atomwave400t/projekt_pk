package warstwa_biznesowa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Pracownik1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imie;
    private String nazwisko;
    private String numerTelefonu;
    private String adres;
    private String miasto;
    private String stanowisko;
    private float pensja;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dataRozpoczeciaPracy;

    public Long getId() {
        return id;
    }

    public Long getId_() {
        if(id==null)
            return new Long(0);
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
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
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pracownik1 other = (Pracownik1) obj;
        if (this.pensja != other.pensja) {
            return false;
        }
        if (!Objects.equals(this.imie, other.nazwisko)) {
            return false;
        }
        if (!Objects.equals(this.nazwisko, other.nazwisko)) {
            return false;
        }
        if (!Objects.equals(this.numerTelefonu, other.numerTelefonu)) {
            return false;
        }
        if (!Objects.equals(this.adres, other.adres)) {
            return false;
        }
        if (!Objects.equals(this.miasto, other.miasto)) {
            return false;
        }
        if (!Objects.equals(this.stanowisko, other.stanowisko)) {
            return false;
        }
        if (!Objects.equals(this.dataRozpoczeciaPracy, other.dataRozpoczeciaPracy)) {
            return false;
        }
        return true;
    }
   
    @Override
    public String toString() {
        return "warstwa_biznesowa.entity.Pracownik1[ id=" + id + " ]";
    }

}
