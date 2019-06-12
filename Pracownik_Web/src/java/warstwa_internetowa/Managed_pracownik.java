package warstwa_internetowa;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.convert.NumberConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pomoc.JsfUtil;
import pomoc.PaginationHelper;
import pomoc.Zmiana_danych;
import warstwa_biznesowa.dto.Pracownik_dto;
import warstwa_biznesowa_ejb.Fasada_warstwy_biznesowej_ejbRemote;

@Named(value = "managed_pracownik")
@SessionScoped
public class Managed_pracownik implements ActionListener, Serializable{
    
    @EJB
    private Fasada_warstwy_biznesowej_ejbRemote fasada;

    public Fasada_warstwy_biznesowej_ejbRemote getFasada() {
        return fasada;
    }
    
    public void setFasada(Fasada_warstwy_biznesowej_ejbRemote fasada) {
        this.fasada = fasada;
    }
   
    public Managed_pracownik() { }
    Pracownik_dto pracownik_dto_przed;
    private DataModel items;
    private int stan = 1;
    private PaginationHelper pagination;
    private Pracownik_dto pracownik_dto = new Pracownik_dto();
    private Zmiana_danych zmiana1= new Zmiana_danych("nazwa");
    private Zmiana_danych zmiana2= new Zmiana_danych("cena");
    public Zmiana_danych getZmiana1()             {  return zmiana1;  }
    public void setZmiana1(Zmiana_danych zmiana2)  {  this.zmiana1 = zmiana2;    }
    public Zmiana_danych getZmiana2()             {  return zmiana2;    }
    public void setZmiana2(Zmiana_danych zmiana2) {  this.zmiana2 = zmiana2;    }
    private int powrot = 1;
    private int zmiana = 1;

    public int getZmiana() {
        return zmiana;
    }
    
    public int getPowrot() {
        return powrot;
    }
    

    public String prepareEdit() {
        pracownik_dto = (Pracownik_dto) items.getRowData();
        pracownik_dto_przed= new Pracownik_dto(pracownik_dto);
        zmiana = 0;
        return "dodaj_pracownik2";
    }

    public String prepareView() {
        pracownik_dto = (Pracownik_dto) items.getRowData();
        powrot = 0;
        stan=1;
        return "rezultat2";
    }

    public String powrot() {
        powrot = 1;
        pracownik_dto = new Pracownik_dto();
        return "lista_pracownikow";
    }
    
    public String update() {
        try {
            boolean wynik=getFasada().edit(pracownik_dto_przed, pracownik_dto);
            pracownik_dto = new Pracownik_dto();
            zmiana = 1;
            recreateModel();
            if(wynik)
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString(
                                                                    "Pracownik_zmieniony"));
            else throw new Exception();
            return "lista_pracownikow";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e,
            ResourceBundle.getBundle("/Bundle").getString("Blad_modyfikacji"));
            return "lista_pracownikow";
        }
    }
    
    public DataModel getItems() {
        if (items == null || fasada.isStan()) {
            items = getPagination().createPageDataModel();
        }
        zmiana = 1;
        powrot = 1;
        return items;
    }
    
    private NumberConverter number_convert = new NumberConverter();

    public NumberConverter getNumber_convert() {
        this.number_convert.setPattern("######.## zł");
        return number_convert;
    }

    public void setNumber_convert(NumberConverter Number_convert) {
        this.number_convert = Number_convert;
    }

    public int getMin() {
        return 0;
    }

    public int getMax() {
        return 100;
    }

    public int getStan() {
        return stan;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }
    
    public String getImie() {
        return pracownik_dto.getImie();
    }

    public void setImie(String imie) {
        this.pracownik_dto.setImie(imie);
    }
    
    public void setNazwisko(String nazwisko) {
        this.pracownik_dto.setNazwisko(nazwisko);
    }
    
    public String getNazwisko() {
        return pracownik_dto.getNazwisko();
    }
   
    public void setNumerTelefonu(String numerTelefonu) {
        this.pracownik_dto.setNumerTelefonu(numerTelefonu);
    }
    
    public String getNumerTelefonu() {
        return pracownik_dto.getNumerTelefonu();
    }
    
    public void setAdres(String adres) {
        this.pracownik_dto.setAdres(adres);
    }
    
    public String getAdres() {
        return pracownik_dto.getAdres();
    }
    
    public void setMiasto(String miasto) {
        this.pracownik_dto.setMiasto(miasto);
    }
    
    public String getMiasto() {
        return pracownik_dto.getMiasto();
    }
    
    public void setStanowisko(String stanowisko) {
        this.pracownik_dto.setStanowisko(stanowisko);
    }
    
    public String getStanowisko() {
        return pracownik_dto.getStanowisko();
    }
    
    public float getPensja() {
        return pracownik_dto.getPensja();
    }
    
    public Date getDataRozpoczeciaPracy() {
        return pracownik_dto.getDataRozpoczeciaPracy();
    }

    public void setDataRozpoczeciaPracy(Date dataRozpoczecia) {
        this.pracownik_dto.setDataRozpoczeciaPracy(dataRozpoczecia);
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public void dodaj_pracownik() {
        fasada.utworz_pracownika(pracownik_dto);
        powrot = 1;
        zmiana = 1;
        dane_pracownika();
        recreateModel();
        getPagination().nextPage();
    }

    public void dane_pracownika() {
        stan = 1;
        pracownik_dto = fasada.dane_pracownika();
        if (pracownik_dto == null){
            pracownik_dto = new Pracownik_dto();
            stan = 0;
        }
    }

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException
    {
       dodaj_pracownik();
    }
   
   
    public DataModel utworz_DataModel() {
        return new ListDataModel(fasada.items_());
    }
    
    public void zakrespromocji(FacesContext context,
            UIComponent toValidate, Object value) {
        stan = 1;
        int input = ((Long) value).intValue();
        if (input < getMin() || input > getMax()) {
            ((UIInput) toValidate).setValid(false);
            FacesMessage message = new FacesMessage("Dane poza zakresem");
            context.addMessage(toValidate.getClientId(context), message);
            stan = 0;
        }
    }
     
    public PaginationHelper getPagination() {
        if(pagination == null) {
            pagination = new PaginationHelper(3){
                @Override
                public int getItemsCount() {
                    return getFasada().count();
                }
                @Override
                public DataModel createPageDataModel() {
                    int[] range = {getPageFirstItem(), getPageLastItem() + 1};
                    return new ListDataModel(getFasada().findRange(range));
                }
            };
        }
        return pagination;
    }
    
    private void recreateModel() {
        items = null;
    }
    
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "lista_pracownikow";
    }
    
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "lista_pracownikow";
    }
    
    private void performDestroy() {
        try {
            getFasada().remove(pracownik_dto);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Usunieto_pracownik"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("Blad_usuwania"));
            
        }
    }
    
    public String destroy() {
        pracownik_dto = (Pracownik_dto) items.getRowData();
        int ile = items.getRowCount();
        if (ile == 1) {
            this.getPagination().previousPage();
        }
        performDestroy();
        return "lista_pracownikow";
    }
    
    public String refresh() {
        getPagination().updatePage();
        items = getPagination().createPageDataModel();
        return "lista_pracownikow";
    }

    public String zapisz() {
        fasada.zapisz();
        return "/faces/index1";
    }
    public String pobierz() {
        fasada.pobierz();
        refresh();
        return "/faces/index1";
    }
}
