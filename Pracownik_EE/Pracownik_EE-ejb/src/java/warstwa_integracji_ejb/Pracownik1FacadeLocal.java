/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_integracji_ejb;

import java.util.List;
import javax.ejb.Local;
import warstwa_biznesowa.entity.Pracownik1;

/**
 *
 * @author Krzychu
 */
@Local
public interface Pracownik1FacadeLocal {

    void create(Pracownik1 pracownik1);

    void edit(Pracownik1 pracownik1);

    void remove(Pracownik1 pracownik1);

    Pracownik1 find(Object id);

    List<Pracownik1> findAll();

    List<Pracownik1> findRange(int[] range);

    int count();
    
}
