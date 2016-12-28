/*
 */
package ch.unibe.iwiqa.entity.dao;

import ch.unibe.iwiqa.entity.FoKoRegistration;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marc Jost
 */
@Stateless
public class FoKoRegistrationFacade extends AbstractFacade<FoKoRegistration> {

    @PersistenceContext(unitName = "_iwiqaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FoKoRegistrationFacade() {
        super(FoKoRegistration.class);
    }
    
}
