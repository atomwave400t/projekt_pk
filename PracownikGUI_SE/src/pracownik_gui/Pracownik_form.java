/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pracownik_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.PatternSyntaxException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import warstwa_biznesowa.dto.Pracownik_dto;

/**
 *
 * @author Kruczkiewicz
 */
public class Pracownik_form extends JPanel implements ActionListener  {
    //utworzenie obiektow odpowiednio etykiet i pól do wprowadzania danych
    JLabel limie = new JLabel("Imie");
    JTextField imie = new JTextField(15);
    JLabel lnazwisko = new JLabel("Nazwisko");
    JTextField nazwisko = new JTextField(15);
    JLabel lnumerTelefonu = new JLabel("Numer Telefonu");
    JTextField numerTelefonu = new JTextField(15);
    JLabel ladres = new JLabel("Adres");
    JTextField adres = new JTextField(15);
    JLabel lmiasto = new JLabel("Miasto");
    JTextField miasto = new JTextField(15);
    JLabel lstanowisko = new JLabel("Stanowisko");
    JTextField stanowisko = new JTextField(15);
    JLabel lpensja = new JLabel("Pensja");
    JTextField pensja = new JTextField(15);
    JLabel ldataRozpoczeciaPracy = new JLabel("Data rozp. pracy");
    JTextField dataRozpoczeciaPracy = new JTextField(15);    
    JButton dodaj_pracownik = new JButton("Dodaj pracownik");	//utworzenie przycisku do wywołania akcji dodania pracownika w aplikacji 
 
public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  	//dodanie sposobu rozmieszczania elementów formularza
        //dodanie powyżej utworzonych elementów do JPanelu
        add(limie);
        add(imie);
        add(lnazwisko);
        add(nazwisko);
        add(lnumerTelefonu);
        add(numerTelefonu);
        add(ladres);
        add(adres);
        add(lmiasto);
        add(miasto);
        add(lstanowisko);
        add(stanowisko);
        add(lpensja);
        add(pensja);
        add(ldataRozpoczeciaPracy);
        add(dataRozpoczeciaPracy);
        dodaj_pracownik.addActionListener(this);	//przycisk uruchamiający zdarzenie po kliknieciu
		                      	 	//obslugiwany przez obiekt typu Pracownik_form za pomoca metody actionPerformed
        add(dodaj_pracownik);			//dodanie przycisku do obiektu typu JPanel
    }

  public String content_validate(JTextField val, int typ) {//walidacja danych 
        String s = val.getText();
        if (s.equals("") || s.length() > 15) { 		//sprawdzenie, czy lancuch jest pusty lub dluzszy niż 15 znakow
            JOptionPane.showMessageDialog(this, "Lancuch danych jest dluzszy niz 15 lub jest pusty");
            return null;
        } else {
            s = s.replaceAll(" ", "_"); 		//wyeliminowanie spacji z lancucha
            val.setText(s);
        }
        if (typ == 1) {		                          // jesli sa dane liczbowe, sprawdzenie, czy można dokonać parsowania na liczbe
            try {                  
                Float.parseFloat(s);
            } catch (NumberFormatException e) {
		 JOptionPane.showMessageDialog(this, "Blad formatu danych liczbowych");
              	 return null;
            }
        }
        return s;
    }
 public String[] form_pracownik() {
        if (content_validate(imie, 0) == null) 			//walidacja danych nazwy jako lancucha
            return null;
        if (content_validate(nazwisko, 0) == null) 			//walidacja danych nazwy jako lancucha
            return null;
        if (content_validate(numerTelefonu, 0) == null) 			//walidacja danych nazwy jako lancucha
            return null;
        if (content_validate(adres, 0) == null) 			//walidacja danych nazwy jako lancucha
            return null; 
        if (content_validate(miasto, 0) == null) 			//walidacja danych nazwy jako lancucha
            return null;
        if (content_validate(stanowisko, 0) == null) 			//walidacja danych nazwy jako lancucha
            return null;
        if (content_validate(pensja, 1) == null) 			//walidacja danych nazwy jako lancucha
            return null;
        String dane[] = {(String) imie.getText(), (String) nazwisko.getText(), (String) numerTelefonu.getText(), (String) adres.getText(), (String) miasto.getText(), (String) stanowisko.getText(), (String) pensja.getText()};	//tablica z danymi pracownika
        return dane;
    }

    public Date data() {
        if (content_validate(dataRozpoczeciaPracy, 0) == null)			 //walidacja danych daty jako lancucha
            return null;
        int rok, miesiac, dzien;
        String data1 = dataRozpoczeciaPracy.getText();
        try {
            String[] data2 = data1.split("-");	//podzial lancucha daty np 12-12-2016 na tablicę lancuchow, reprezentujacych elementy daty
            rok = Integer.parseInt(data2[2]);
            miesiac = Integer.parseInt(data2[1]);
            dzien = Integer.parseInt(data2[0]);
        } catch (PatternSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {        //kontrola poprawności 	 	JOptionPane.showMessageDialog(this, "Blad formatu daty");		         //formatu daty
                          return null;
        }
        GregorianCalendar gc = new GregorianCalendar();		//pomocnicza klasa do utworzenia daty 
        gc.set(rok, miesiac - 1, dzien);			//tworzenie daty
        return gc.getTime();				//pobranie daty jako obiektu typu Date
    }
    
 @Override
    public void actionPerformed(ActionEvent evt) {	//obsluga zdarzenia kliknięcia na przycisk "Dodaj_pracownik"
        String[] dane = form_pracownik(); 		//utworzenie tablicy z danymi pracownika: nazwa, cena, promocja
        if (dane == null) {
            return;
        }
        Date data_ = data();			//utworzenie daty
        if (data_ == null) {
            return;
        }
        Pracownik_dto pracownik = new Pracownik_dto(dane, data_);
        GUI_main.getFacade().utworz_pracownika(pracownik);	// wywołanie metody logiki biznesowej tworzacej obiekt typu Pracownik1
    }
}
