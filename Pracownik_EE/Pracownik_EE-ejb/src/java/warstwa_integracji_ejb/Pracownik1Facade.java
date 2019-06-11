/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_integracji_ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import warstwa_biznesowa.entity.Pracownik1;

/**
 *
 * @author Krzychu
 */
@Stateless
public class Pracownik1Facade extends AbstractFacade<Pracownik1> implements Pracownik1FacadeLocal {

    @PersistenceContext(unitName = "Pracownik_EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Pracownik1Facade() {
        super(Pracownik1.class);
    }
    
}
