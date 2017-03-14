/*
 */
package ch.unibe.iwiqa.entity.dao;

import ch.unibe.iwiqa.entity.Advisor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marc Jost
 */
@Stateless
public class AdvisorFacade extends AbstractFacade<Advisor> {

    @PersistenceContext(unitName = "_iwiqaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdvisorFacade() {
        super(Advisor.class);
    }
    
    public List<Advisor> findByEmail(String email) {
        return em.createNamedQuery("Advisor.findByEmail").setParameter("email", email).getResultList();
    }

    public List<Advisor> findAllActiveAdvisors() {
        return em.createNamedQuery("Advisor.findAllActiveAdvisors").getResultList();
    }
    
}
