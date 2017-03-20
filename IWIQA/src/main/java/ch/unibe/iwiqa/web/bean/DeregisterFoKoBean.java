/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import ch.unibe.iwiqa.entity.dao.FoKoRegistrationFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.entity.dao.StudentFacade;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Marc Jost
 */
@RequestScoped
public class DeregisterFoKoBean {
    
    @Inject
    private FoKoFacade foKoFacade;
    
    @Inject
    private StudentFacade studentFacade;
    
    @Inject
    private FoKoRegistrationFacade foKoRegistrationFacade;
    
    @Inject
    private QAFacade qAFacade;
    
    public void deregisterFoKo(FoKoRegistration reg){
        Student s = reg.getStudent();
        s.removeFoKoRegistration(reg);
        FoKo f = reg.getFoko();
        f.removeParticipant(reg);
        QA q = reg.getQa();
        q.removeFoKoRegistration(reg);
        
        studentFacade.edit(s);
        foKoFacade.edit(f);
        qAFacade.edit(q);
        foKoRegistrationFacade.remove(reg);
    }
    
}
