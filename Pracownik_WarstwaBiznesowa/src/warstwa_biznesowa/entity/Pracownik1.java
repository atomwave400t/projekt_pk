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
    private String nazwa;
    private String producent;
    private float cena;
    private int promocja;
    private int ilosc;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    Date data_produkcji;
    @Temporal(javax.persistence.TemporalType.DATE)
    Date data_przydatnosci;

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public Date getData_przydatnosci() {
        return data_przydatnosci;
    }

    public void setData_przydatnosci(Date data_przydatnosci) {
        this.data_przydatnosci = data_przydatnosci;
    }

    public float getCena() {
        return cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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

    public Date getData_produkcji() {
        return data_produkcji;
    }

    public void setData_produkcji(Date data_produkcji) {
        this.data_produkcji = data_produkcji;
    }

  

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
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
        if (Float.floatToIntBits(this.cena) != Float.floatToIntBits(other.cena)) {
            return false;
        }
        if (this.promocja != other.promocja) {
            return false;
        }
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
        if (!Objects.equals(this.producent, other.producent)) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return "warstwa_biznesowa.entity.Pracownik1[ id=" + id + " ]";
    }

    public float cena_brutto() {
      return  cena * (1 - (float) promocja / 100);
    }

}
