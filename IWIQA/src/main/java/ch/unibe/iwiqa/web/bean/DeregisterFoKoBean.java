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
import ch.unibe.iwiqa.web.mail.MailNotificationManagerBean;
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
    
    @Inject
    private MailNotificationManagerBean mailNotificationManagerBean;
    
    public void deregisterFoKo(FoKoRegistration reg){
        mailNotificationManagerBean.sendAdvisorFoKoRegistrationCancelled(reg);
        
        Student s = reg.getStudent();
        s.removeFoKoRegistration(reg);
        studentFacade.edit(s);
        
        FoKo f = reg.getFoko();
        f.removeParticipant(reg);
        foKoFacade.edit(f);
        
        QA q = reg.getQa();
        q.removeFoKoRegistration(reg);
        qAFacade.edit(q);
        
        foKoRegistrationFacade.remove(reg);
    }
    
}
