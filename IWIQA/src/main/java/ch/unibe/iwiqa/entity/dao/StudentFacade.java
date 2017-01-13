/*
 */
package ch.unibe.iwiqa.entity.dao;

import ch.unibe.iwiqa.entity.Student;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Marc Jost
 */
@Stateless
public class StudentFacade extends AbstractFacade<Student> {

    @PersistenceContext(unitName = "_iwiqaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentFacade() {
        super(Student.class);
    }

    public List<Student> findByEmail(String email) {
        return em.createNamedQuery("Student.findByEmail").setParameter("email", email).getResultList();
    }
    
}
