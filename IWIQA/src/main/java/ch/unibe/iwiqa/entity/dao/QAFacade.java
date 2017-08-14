/*
 */
package ch.unibe.iwiqa.entity.dao;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.QA;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marc Jost
 */
@Stateless
public class QAFacade extends AbstractFacade<QA> {

    @PersistenceContext(unitName = "_iwiqaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QAFacade() {
        super(QA.class);
    }

    public List<QA> findOpenAndNotAbortedQAsByAdvisor(Advisor advisor) {
        return em.createNamedQuery("QA.findOpenAndNotAbortedQAsByAdvisor").setParameter("advisor", advisor).getResultList();
    }
    
    public List<QA> findOpenUnremindedOfEndingInOneWeekQAs() {
        return em.createNamedQuery("QA.findOpenUnremindedOfEndingInOneWeekQAs").getResultList();
    }
    
    public List<QA> findHandedInQAs() {
        return em.createNamedQuery("QA.findHandedInQAs").getResultList();
    }
    
    public List<QA> findAllOpenAndNotAbortedQAs() {
        return em.createNamedQuery("QA.findAllOpenAndNotAbortedQAs").getResultList();
    }
}
