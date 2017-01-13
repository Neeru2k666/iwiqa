/*
 */
package ch.unibe.iwiqa.dev;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import ch.unibe.iwiqa.entity.dao.FoKoRegistrationFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.entity.dao.StudentFacade;
import ch.unibe.iwiqa.util.FoKo_ParticipateAs;
import ch.unibe.iwiqa.util.QA_Status;
import ch.unibe.iwiqa.util.QA_Type;
import java.util.Calendar;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 *
 * @author Marc Jost
 */
@Startup
@Singleton
public class DevelopmentTestData {

    @Inject
    private AdvisorFacade advisorFacade;

    @Inject
    private StudentFacade studentFacade;
    
    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private FoKoFacade foKoFacade;
    
    @Inject
    private FoKoRegistrationFacade foKoRegistrationFacade;

    @PostConstruct
    public void initializeData() {
        createTestAdvisors();
        createTestStudents();
        createTestQAs();
        createFoKos();
        createFoKoRegistrations();
    }

    private void createTestAdvisors() {
        Advisor adv1 = new Advisor();
        adv1.setEmail("a1@a.ch");
        adv1.setFirstName("Oliver");
        adv1.setLastName("Krancher");
        adv1.setPassword(new Sha256Hash("123").toHex());

        advisorFacade.create(adv1);

        Advisor adv2 = new Advisor();
        adv2.setEmail("a2@a.ch");
        adv2.setFirstName("Thomas");
        adv2.setLastName("Hurni");
        adv2.setPassword(new Sha256Hash("123").toHex());

        advisorFacade.create(adv2);
    }

    private void createTestStudents() {
        Student s1 = new Student();
        s1.setFirstName("Marc");
        s1.setLastName("Jost");
        s1.setMatNr("12-108-130");
        s1.setEmail("s1@s.ch");
        s1.setStreet("Stöckackerstrasse 98");
        s1.setCity("Bern");
        s1.setZIP("3018");
        s1.setPassword(new Sha256Hash("123").toHex());
        
        studentFacade.create(s1);
        
        Student s2 = new Student();
        s2.setFirstName("Nicole");
        s2.setLastName("Buys");
        s2.setMatNr("12-108-130");
        s2.setEmail("s2@s.ch");
        s2.setStreet("Buysstrasse 123");
        s2.setCity("Bern");
        s2.setZIP("3000");
        s2.setPassword(new Sha256Hash("123").toHex());
        
        studentFacade.create(s2);
        
        Student s3 = new Student();
        s3.setFirstName("David");
        s3.setLastName("Bucher");
        s3.setMatNr("12-108-130");
        s3.setEmail("s3@s.ch");
        s3.setStreet("Bucherstrasse 123");
        s3.setCity("Bern");
        s3.setZIP("3000");
        s3.setPassword(new Sha256Hash("123").toHex());
        
        studentFacade.create(s3);
    }
    
    private void createTestQAs() {
        Student s1 = studentFacade.findByEmail("s1@s.ch").get(0);
        Student s2 = studentFacade.findByEmail("s2@s.ch").get(0);
        Student s3 = studentFacade.findByEmail("s3@s.ch").get(0);
        Advisor a1 = advisorFacade.findByEmail("a1@a.ch").get(0);
        QA qa1 = new QA();
        qa1.setTitle("Sprachaufenthalte: Warum es doch eigentlich Ferien sind");
        Calendar start = Calendar.getInstance();
        start.set(2017, 1, 1);
        qa1.setStartingDate(start);
        Calendar end = Calendar.getInstance();
        end.set(2017, 1, 1);
        end.add(Calendar.WEEK_OF_YEAR, 10);
        qa1.setEndingDate(end);
        qa1.setQaType(QA_Type.BA.getAbbreviated());
        qa1.setStatus(QA_Status.QA_GRADED);
        qa1.setStudent(s1);
        qa1.setAdvisor(a1);
        qa1.setGrade(6);
        qAFacade.create(qa1);
        s1.addQA(qa1);
        studentFacade.edit(s1);
        a1.addQA(qa1);
        advisorFacade.edit(a1);
        
        QA qa2 = new QA();
        qa2.setTitle("Schnell WMS Prüfungen korrigieren: Erfahrungen aus erster Hand");
        qa2.setStartingDate(start);
        qa2.setEndingDate(end);
        qa2.setQaType(QA_Type.BA.getAbbreviated());
        qa2.setStatus(QA_Status.PROPOSAL_IN_PROGRESS);
        qa2.setStudent(s2);
        qa2.setAdvisor(a1);
        qAFacade.create(qa2);
        s2.addQA(qa2);
        studentFacade.edit(s2);
        a1.addQA(qa2);
        advisorFacade.edit(a1);
        
        QA qa3 = new QA();
        qa3.setTitle("Multiple Cases Analysis of Platform-as-a-Service implementations");
        qa3.setStartingDate(start);
        qa3.setEndingDate(end);
        qa3.setQaType(QA_Type.MA.getAbbreviated());
        qa3.setStatus(QA_Status.PROPOSAL_ACCEPTED);
        qa3.setStudent(s1);
        qa3.setAdvisor(a1);
        qAFacade.create(qa3);
        s1.addQA(qa3);
        studentFacade.edit(s1);
        a1.addQA(qa3);
        advisorFacade.edit(a1);
        
        QA qa4 = new QA();
        qa4.setTitle("Sprachaufenthalte: Warum es doch eigentlich Ferien sind");
        qa4.setStartingDate(start);
        qa4.setEndingDate(end);
        qa4.setQaType(QA_Type.BA.getAbbreviated());
        qa4.setStatus(QA_Status.QA_HANDED_IN);
        qa4.setStudent(s3);
        qa4.setAdvisor(a1);
        qAFacade.create(qa4);
        s3.addQA(qa4);
        studentFacade.edit(s3);
        a1.addQA(qa4);
        advisorFacade.edit(a1);
    }
    
    private void createFoKos() {
        FoKo f1 = new FoKo();
        Calendar start = Calendar.getInstance();
        start.set(2017, 5, 12, 14, 0);
        f1.setStartingDate(start);
        Calendar end = Calendar.getInstance();
        end.set(2017, 5, 12, 15, 0);
        f1.setEndingDate(end);
        f1.setRoom("Engehaldestrasse 8, Raum 101");
        
        foKoFacade.create(f1);
        
        FoKo f2 = new FoKo();
        Calendar start2 = Calendar.getInstance();
        start2.set(2017, 7, 8, 9, 30);
        f2.setStartingDate(start2);
        Calendar end2 = Calendar.getInstance();
        end2.set(2017, 7, 8, 11, 45);
        f2.setEndingDate(end2);
        f2.setRoom("Engehaldestrasse 8, Raum 101");
        
        foKoFacade.create(f2);
    }

    private void createFoKoRegistrations() {
        FoKo f1 = foKoFacade.findAll().get(0);
        QA qa1 = qAFacade.findAll().get(0);
        
        FoKoRegistration fr1 = new FoKoRegistration();
        fr1.setFoko(f1);
        fr1.setQa(qa1);
        fr1.setParticipatingAs(FoKo_ParticipateAs.LISTENER);
        foKoRegistrationFacade.create(fr1);
        f1.addParticipant(fr1);
        foKoFacade.edit(f1);
        qa1.addFoKoRegistration(fr1);
        qAFacade.edit(qa1);
    }
}
