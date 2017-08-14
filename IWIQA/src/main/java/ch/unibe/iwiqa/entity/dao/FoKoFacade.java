/*
 */
package ch.unibe.iwiqa.entity.dao;

import ch.unibe.iwiqa.entity.FoKo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marc Jost
 */
@Stateless
public class FoKoFacade extends AbstractFacade<FoKo> {

    @PersistenceContext(unitName = "_iwiqaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FoKoFacade() {
        super(FoKo.class);
    }

    public List<FoKo> findAllOrderByDate() {
        return em.createNamedQuery("FoKo.findAllOrderByDate").getResultList();
    }
    
    public List<FoKo> findAllInFutureOrderByDate(){
        return em.createNamedQuery("FoKo.findAllInFutureOrderByDate").getResultList();
    }
    
    public List<FoKo> findUnremindedInFutureFoKos() {
        return em.createNamedQuery("FoKo.findUnremindedInFutureFoKos").getResultList();
    }
    
}
