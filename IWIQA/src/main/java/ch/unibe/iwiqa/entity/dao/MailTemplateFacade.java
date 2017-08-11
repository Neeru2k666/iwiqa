/*
 */
package ch.unibe.iwiqa.entity.dao;

import ch.unibe.iwiqa.entity.MailTemplate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marc Jost
 */
@Stateless
public class MailTemplateFacade extends AbstractFacade<MailTemplate> {

    @PersistenceContext(unitName = "_iwiqaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MailTemplateFacade() {
        super(MailTemplate.class);
    }
    
    public List<MailTemplate> findByTemplateName(String templateName) {
        return em.createNamedQuery("MailTemplate.findByTemplateName").setParameter("templateName", templateName).getResultList();
    }
    
}
