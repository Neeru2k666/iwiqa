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
import ch.unibe.iwiqa.util.FoKo_ParticipateAs;
import ch.unibe.iwiqa.web.mail.MailNotificationManagerBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Marc Jost
 */
@RequestScoped
public class RegisterFoKoBean {
    
    @Inject
    private FoKoFacade foKoFacade;

    @Inject
    private FoKoRegistrationFacade foKoRegistrationFacade;

    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private StudentFacade studentFacade;
    
    @Inject
    private MailNotificationManagerBean mailNotificationManagerBean;

    public void registerToFoKo(FoKo foko, QA qa, Student student, FoKo_ParticipateAs participateAs) {
        FoKoRegistration reg = new FoKoRegistration();
        reg.setFoko(foko);
        reg.setQa(qa);
        reg.setStudent(student);
        reg.setParticipatingAs(participateAs);
        foKoRegistrationFacade.create(reg);

        foko.addParticipant(reg);
        foKoFacade.edit(foko);

        student.addFoKoRegistration(reg);
        studentFacade.edit(student);

        qa.addFoKoRegistration(reg);
        qAFacade.edit(qa);
        
        mailNotificationManagerBean.sendAdvisorFoKoRegistration(reg);
    }
}
