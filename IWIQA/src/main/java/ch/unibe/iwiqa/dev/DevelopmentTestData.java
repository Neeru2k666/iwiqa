/*
 */
package ch.unibe.iwiqa.dev;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.IWIQASettings;
import ch.unibe.iwiqa.entity.MailTemplate;
import ch.unibe.iwiqa.entity.Professor;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import ch.unibe.iwiqa.entity.dao.FoKoRegistrationFacade;
import ch.unibe.iwiqa.entity.dao.IWIQASettingsFacade;
import ch.unibe.iwiqa.entity.dao.MailTemplateFacade;
import ch.unibe.iwiqa.entity.dao.ProfessorFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.entity.dao.StudentFacade;
import ch.unibe.iwiqa.util.FoKo_ParticipateAs;
import ch.unibe.iwiqa.util.QA_Status;
import ch.unibe.iwiqa.util.QA_Type;
import java.util.Calendar;
import java.util.Date;
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

    @Inject
    private ProfessorFacade professorFacade;

    @Inject
    private MailTemplateFacade mailTemplateFacade;
    
    @Inject
    private IWIQASettingsFacade iWIQASettingsFacade;

    @PostConstruct
    public void initializeData() {
        createTestProfessors();
        createTestAdvisors();
        createTestStudents();
        createTestQAs();
        createFoKos();
        createFoKoRegistrations();
        createMailTemplates();
        createIWIQASettings();
    }

    private void createTestProfessors() {
        Professor prof = new Professor();
        prof.setFirstName("Jens");
        prof.setLastName("Dibbern");
        prof.setTitles("Prof. Dr.");

        professorFacade.create(prof);

        Professor prof2 = new Professor();
        prof2.setFirstName("VornameProf");
        prof2.setLastName("NachnameProf");
        prof2.setTitles("Dipl. Phil. Nat.");

        professorFacade.create(prof2);
    }

    private void createTestAdvisors() {
        Professor p1 = professorFacade.findAll().get(0);

        Advisor adv1 = new Advisor();
        adv1.setEmail("a1@a.ch");
        adv1.setFirstName("Oliver");
        adv1.setLastName("Krancher");
        adv1.setPassword(new Sha256Hash("123").toHex());
        adv1.setProfessor(p1);
        advisorFacade.create(adv1);
        p1.addAdvisor(adv1);
        professorFacade.edit(p1);

        Advisor adv2 = new Advisor();
        adv2.setEmail("a2@a.ch");
        adv2.setFirstName("Thomas");
        adv2.setLastName("Hurni");
        adv2.setPassword(new Sha256Hash("123").toHex());
        adv2.setProfessor(p1);
        advisorFacade.create(adv2);
        p1.addAdvisor(adv2);
        professorFacade.edit(p1);

        Advisor adv3 = new Advisor();
        adv3.setEmail("a3@a.ch");
        adv3.setFirstName("Inactive");
        adv3.setLastName("Advisor");
        adv3.setActive(false);
        adv3.setPassword(new Sha256Hash("123").toHex());
        adv3.setProfessor(p1);
        advisorFacade.create(adv3);
        p1.addAdvisor(adv3);
        professorFacade.edit(p1);
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

        Student s4 = new Student();
        s4.setFirstName("Marc");
        s4.setLastName("Jost");
        s4.setMatNr("12-108-130");
        s4.setEmail("marc.jost@digital-front.ch");
        s4.setStreet("Stöckackerstrasse 98");
        s4.setCity("Bern");
        s4.setZIP("3018");
        s4.setPassword(new Sha256Hash("123").toHex());

        studentFacade.create(s4);
    }

    private void createTestQAs() {
        Student s1 = studentFacade.findByEmail("s1@s.ch").get(0);
        Student s2 = studentFacade.findByEmail("s2@s.ch").get(0);
        Student s3 = studentFacade.findByEmail("s3@s.ch").get(0);
        Advisor a1 = advisorFacade.findByEmail("a1@a.ch").get(0);
        QA qa1 = new QA();
        qa1.setTitle("Sprachaufenthalte: Warum es doch eigentlich Ferien sind");
        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, 2017);
        start.set(Calendar.MONTH, 0);
        start.set(Calendar.DAY_OF_MONTH, 1);
        qa1.setStartingDate(start.getTime());
        Calendar end = Calendar.getInstance();
        end.set(Calendar.YEAR, 2017);
        end.set(Calendar.MONTH, 0);
        end.set(Calendar.DAY_OF_MONTH, 1);
        end.add(Calendar.DAY_OF_YEAR, 70);
        qa1.setEndingDate(end.getTime());
        qa1.setQaType(QA_Type.BA);
        qa1.setStatus(QA_Status.QA_ABORTED);
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
        qa2.setStartingDate(start.getTime());
        qa2.setEndingDate(end.getTime());
        qa2.setQaType(QA_Type.BA);
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
        qa3.setStartingDate(start.getTime());
        qa3.setEndingDate(end.getTime());
        qa3.setQaType(QA_Type.MA);
        qa3.setStatus(QA_Status.QA_COMPLETED);
        qa3.setHandInDate(new Date());
        qa3.setGradedDate(end.getTime());
        qa3.setStudent(s1);
        qa3.setAdvisor(a1);
        qAFacade.create(qa3);
        s1.addQA(qa3);
        studentFacade.edit(s1);
        a1.addQA(qa3);
        advisorFacade.edit(a1);

        QA qa4 = new QA();
        qa4.setTitle("Über Swag und schöne Fahrräder: Eine (Un-)Fallstudie");
        qa4.setStartingDate(start.getTime());
        qa4.setEndingDate(end.getTime());
        qa4.setHandInDate(new Date());
        qa4.setQaType(QA_Type.BA);
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
        f1.setStartingDate(start.getTime());
        f1.setRoom("Engehaldestrasse 8, Raum 101");

        foKoFacade.create(f1);

        FoKo f2 = new FoKo();
        Calendar start2 = Calendar.getInstance();
        start2.set(2017, 11, 8, 9, 30);
        f2.setStartingDate(start2.getTime());
        f2.setRoom("Engehaldestrasse 8, Raum 101");

        foKoFacade.create(f2);
    }

    private void createFoKoRegistrations() {
        FoKo f1 = foKoFacade.findAll().get(0);
        QA qa1 = qAFacade.findAll().get(0);

        FoKoRegistration fr1 = new FoKoRegistration();
        fr1.setFoko(f1);
        fr1.setQa(qa1);
        fr1.setStudent(qa1.getStudent());
        fr1.setParticipatingAs(FoKo_ParticipateAs.LISTENER);
        foKoRegistrationFacade.create(fr1);
        f1.addParticipant(fr1);
        foKoFacade.edit(f1);
        qa1.addFoKoRegistration(fr1);
        qAFacade.edit(qa1);
        qa1.getStudent().addFoKoRegistration(fr1);
        studentFacade.edit(qa1.getStudent());
    }

    private void createMailTemplates() {
        MailTemplate mt1 = new MailTemplate();
        mt1.setTemplateName("Mailtemp_A_Foko_Registration");
        mt1.setSubject("IWIQA: FoKo Anmeldung");
        mt1.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {advisor.fullname}</p>\n"
                + "<p>{student.fullname} hat sich soeben an ein Forschungskolloquium als <strong>{reg.participateAs}</strong> angemeldet.</p>\n"
                + "<ul>\n"
                + "<li>Forschungskolloquium: {foko.startingDate}</li>\n"
                + "<li>Student/in: {student.fullname}, {student.matnr}</li>\n"
                + "<li>Typ: {qa.qa_type.unabbreviated}</li>\n"
                + "<li>Titel: {qa.title}</li>\n"
                + "<li>Status: {qa.qa_status}</li>\n"
                + "</ul>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt1);

        MailTemplate mt2 = new MailTemplate();
        mt2.setTemplateName("Mailtemp_A_Foko_Registration_Cancelled");
        mt2.setSubject("IWIQA: FoKo Abmeldung");
        mt2.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {advisor.fullname}</p>\n"
                + "<p>Ein/e Student/in hat sich soeben von einem Foko abgemeldet.</p>\n"
                + "<p>\n"
                + "<ul>\n"
                + "<li>Forschungskolloquium: {foko.startingDate}</li>\n"
                + "<li>Student/in: {student.fullname}, {student.matnr}</li>\n"
                + "<li>Typ: {qa.qa_type.unabbreviated}</li>\n"
                + "<li>Titel: {qa.title}</li>\n"
                + "</ul>\n"
                + "</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt2);

        MailTemplate mt3 = new MailTemplate();
        mt3.setTemplateName("Mailtemp_A_QA_Graded");
        mt3.setSubject("IWIQA: Qualifikationsarbeit bewertet");
        mt3.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Hallo,</p>\n"
                + "<p>Die {qa.qa_type.unabbreviated} von {student.fullname} mit dem Titel \"{qa.title}\" wurde soeben von {advisor.fullname} bewertet. Die Notenmeldung kann nun erfolgen.</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt3);

        MailTemplate mt4 = new MailTemplate();
        mt4.setTemplateName("Mailtemp_A_QA_New");
        mt4.setSubject("IWIQA: Neue Qualifikationsarbeit erfasst");
        mt4.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {advisor.fullname}</p>\n"
                + "<p>Soeben wurde auf IWIQA eine neue {qa.qa_type.unabbreviated} von {student.fullname} mit dem Titel \"{qa.title}\" erfasst und Dir zugewiesen.</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt4);

        MailTemplate mt5 = new MailTemplate();
        mt5.setTemplateName("Mailtemp_A_QA_Reminder_Endingdate_1Week");
        mt5.setSubject("IWIQA: Reminder: Abgabedatum in einer Woche");
        mt5.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {advisor.fullname}</p>\n"
                + "<p>Wir möchten Dich daran erinnern, dass die Abgabe der Qualifikationsarbeit mit dem Titel: \"{qa.title}\" in einer Woche erfolgen muss.</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt5);

        MailTemplate mt6 = new MailTemplate();
        mt6.setTemplateName("Mailtemp_A_QA_Reminder_GradeAnnouncement_1Week");
        mt6.setSubject("IWIQA: Reminder: Notenmeldung ausstehend");
        mt6.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Hallo,</p>\n"
                + "<p>Wir möchten Dich daran erinnern, dass die Bewertung der Qualifikationsarbeit mit dem Titel: \"{qa.title}\" bereits vor einer oder mehreren Wochen stattgefunden hat und die Notenmeldung noch aussteht.</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt6);

        MailTemplate mt7 = new MailTemplate();
        mt7.setTemplateName("Mailtemp_A_QA_Reminder_Grading_1Week");
        mt7.setSubject("IWIQA: Reminder: Bewertung ausstehend");
        mt7.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {advisor.fullname}</p>\n"
                + "<p>Wir möchten Dich daran erinnern, dass die Abgabe der Qualifikationsarbeit mit dem Titel: \"{qa.title}\" bereits vor einer oder mehreren Wochen stattgefunden hat und die Bewertung noch aussteht.</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt7);

        MailTemplate mt8 = new MailTemplate();
        mt8.setTemplateName("Mailtemp_A_QA_Uploaded");
        mt8.setSubject("IWIQA: Qualifikationsarbeit hochgeladen");
        mt8.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {advisor.fullname}</p>\n"
                + "<p>Soeben wurde auf IWIQA die {qa.qa_type.unabbreviated} von {student.fullname} mit dem Titel \"{qa.title}\" hochgeladen.</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt8);

        MailTemplate mt9 = new MailTemplate();
        mt9.setTemplateName("Mailtemp_S_Foko_Reminder_1Week");
        mt9.setSubject("IWIQA: Reminder: FoKo in einer Woche");
        mt9.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {student.fullname}</p>\n"
                + "<p>Sie schreiben im Moment eine Bachelor- oder eine Masterarbeit am IWI. Mit dieser Mail möchten wir Sie auf unser nächstes Forschungskolloquium (Foko) aufmerksam machen:</p>\n"
                + "<p>\n"
                + "<strong>{foko.startingDate.date}, Beginn: {foko.startingDate.time}, {foko.room}</strong>\n"
                + "</p>\n"
                + "<p>\n"
                + "Bitte beachten Sie:\n"
                + "<ul>\n"
                + "<li>Sie müssen an zwei Fokos teilnehmen, davon einmal als Referent/in.</li>\n"
                + "<li>Eine aktuelle Übersicht über geplante Fokos finden Sie auf der IWIQA Plattform.</li>\n"
                + "<li>Um als Referent teilzunehmen, muss Ihr Proposal abgenommen sein. Idealerweise präsentieren Sie etwa in der Mitte Ihrer Arbeit, so dass Sie das Feedback noch einarbeiten können.</li>\n"
                + "<li>Die Präsentationszeit beträgt 15 min.</li>\n"
                + "<li>Wenn Sie als Referent teilnehmen, schicken Sie mir bitte spätestens drei Arbeitstage vor dem Foko Ihre Folien. Sie erhalten dann Feedback von mir, das Sie dann vor der Präsentation einarbeiten würden. Bei empirischen Arbeiten empfehle ich Ihnen, in der Präsentation den Literatur- oder Theorieteil sehr kurz zu halten und ausführlich erste Ergebnisse darzustellen. Zeigen Sie auch auf, mit welchen Fragen oder Problemen Sie gegenwärtig kämpfen, damit wir Ihnen helfen können.</li>\n"
                + "</ul>\n"
                + "</p>\n"
                + "<p>Alles Gute für Ihre Qualifikationsarbeit!</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt9);

        MailTemplate mt10 = new MailTemplate();
        mt10.setTemplateName("Mailtemp_S_Foko_Reminder_3Days");
        mt10.setSubject("IWIQA: Reminder: FoKo in drei Tagen");
        mt10.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {student.fullname}</p>\n"
                + "<p>Sie haben sich für das folgende Forschungskolloquium (Foko) als <strong>{reg.participateAs}</strong> angemeldet. Mit dieser Mail möchten wir Sie erneut an den Termin erinnern:</p>\n"
                + "<p>\n"
                + "<strong>{foko.startingDate.date}, Beginn: {foko.startingDate.time}, {foko.room}</strong>\n"
                + "</p>\n"
                + "<p>Falls Sie den Termin nicht wahrnehmen können, bitten wir Sie, sich auf der IWIQA Plattform vom Foko abzumelden.</p>\n"
                + "<p>Alles Gute für Ihre Qualifikationsarbeit!</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt10);

        MailTemplate mt11 = new MailTemplate();
        mt11.setTemplateName("Mailtemp_S_QA_Reminder_Endingdate_1Week");
        mt11.setSubject("IWIQA: Reminder: Abgabedatum in einer Woche");
        mt11.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {student.fullname}</p>\n"
                + "<p>Wir möchten Sie daran erinnern, dass die Abgabe Ihrer Qualifikationsarbeit mit dem Titel: \"{qa.title}\" in einer Woche erfolgen muss.</p>\n"
                + "<p>Wir wünschen Ihnen alles Gute für den Endspurt!</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt11);

        MailTemplate mt12 = new MailTemplate();
        mt12.setTemplateName("Mailtemp_S_QA_Status_Update");
        mt12.setSubject("IWIQA: Status Update");
        mt12.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {student.fullname}</p>\n"
                + "<p>Der Status Ihrer Qualifikationsarbeit: \"{qa.title}\" wurde auf: \"{qa.qa_status}\" geändert.</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt12);

        MailTemplate mt13 = new MailTemplate();
        mt13.setTemplateName("Mailtemp_S_Regsuccess");
        mt13.setSubject("IWIQA: Registrierung erfolgreich!");
        mt13.setMailBody("<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<p>Liebe/r {student.fullname}</p>\n"
                + "<p>Herzlichen Dank, dass Sie sich auf IWIQA registriert haben. Auf der Plattform können Sie nun Ihre Qualifikationsarbeit erfassen und sich an Forschungskolloquien anmelden.</p>\n"
                + "<p>Alles Gute für Ihre Qualifikationsarbeit!</p>\n"
                + "<p>Liebe Grüsse, IWIQA</p>\n"
                + "</body>\n"
                + "</html>");
        mailTemplateFacade.create(mt13);
    }
    
    private void createIWIQASettings(){
        IWIQASettings settings = new IWIQASettings();
        settings.setGradeAnnouncerEmail("marc.jost@digital-front.ch");
        settings.setPathGradeAnnouncementFolder("S:\\up\\announcements");
        settings.setPathQAFileUploadFolder("S:\\up\\qauploads");
        settings.setPathGradeAnnouncementTemplate("S:\\up\\IWIQA_Notenmeldung.docx");
        
        iWIQASettingsFacade.create(settings);
    }
}
