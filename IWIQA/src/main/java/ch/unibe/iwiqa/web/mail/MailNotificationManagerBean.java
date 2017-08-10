/*
 */
package ch.unibe.iwiqa.web.mail;

import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class MailNotificationManagerBean {

    @Inject
    private MailSenderBean mailSenderBean;

    public void test() {
   
    }

    public void sendStudentRegistrationSuccess(Student student) {
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_S_Regsuccess.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(rawTemplate, student);
            mailSenderBean.send("IWIQA: Registrierung erfolgreich!", "marc.jost@digital-front.ch", mailBody); //TODO: Change
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentRegistrationSuccess()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_Regsuccess.html nicht geladen werden konnte.");
        }
    }

    public void sendStudentQAStatusUpdate(QA qa) {
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_S_QA_Status_Update.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithQA(rawTemplate, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailSenderBean.send("IWIQA: Status Update", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentQAStatusUpdate()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_QA_Status_Update.html nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorQAGraded(QA qa) {
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_A_QA_Graded.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithQA(rawTemplate, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailSenderBean.send("IWIQA: Eine Qualifikationsarbeit wurde bewertet", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorQAGraded()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Graded.html nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorNewQA(QA qa) {
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_A_QA_New.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithQA(rawTemplate, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailSenderBean.send("IWIQA: Eine neue Qualifikationsarbeit wurde erfasst", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorNewQA()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_New.html nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorReminderQAEndingInOneWeek(QA qa){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_A_QA_Reminder_Endingdate_1Week.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithQA(rawTemplate, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailSenderBean.send("IWIQA: Reminder: Abgabedatum in einer Woche", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorReminderQAEndingInOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Reminder_Endingdate_1Week.html nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorReminderQAGradeAnnouncementOneWeek(QA qa){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_A_QA_Reminder_GradeAnnouncement_1Week.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithQA(rawTemplate, qa);
            mailSenderBean.send("IWIQA: Reminder: Notenmeldung ausstehend", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorReminderQAGradeAnnouncementOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Reminder_GradeAnnouncement_1Week.html nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorFoKoRegistration(FoKoRegistration reg){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_A_Foko_Registration.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithFoKoRegistration(rawTemplate, reg);
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, reg.getQa());
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, reg.getQa().getAdvisor());
            mailBody = MailTemplatePopulater.populateTemplateWithFoKo(mailBody, reg.getFoko());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, reg.getStudent());
            mailSenderBean.send("IWIQA: Neue Anmeldung an FoKo", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorFoKoRegistration()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_Foko_Registration.html nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorFoKoRegistrationCancelled(FoKoRegistration reg){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_A_Foko_Registration_Cancelled.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithFoKoRegistration(rawTemplate, reg);
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, reg.getQa());
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, reg.getQa().getAdvisor());
            mailBody = MailTemplatePopulater.populateTemplateWithFoKo(mailBody, reg.getFoko());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, reg.getStudent());
            mailSenderBean.send("IWIQA: Abmeldung von FoKo", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorFoKoRegistrationCancelled()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_Foko_Registration_Cancelled.html nicht geladen werden konnte.");
        }
    }
    
    public void sendStudentReminderQAEndingInOneWeek(QA qa){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_S_QA_Reminder_Endingdate_1Week.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithQA(rawTemplate, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailSenderBean.send("IWIQA: Reminder: Abgabedatum in einer Woche", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentReminderQAEndingInOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_QA_Reminder_Endingdate_1Week.html nicht geladen werden konnte.");
        }
    }
    
    public void sendStudentReminderFoKoInThreeDays(FoKoRegistration reg){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_S_Foko_Reminder_3Days.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithFoKoRegistration(rawTemplate, reg);
            mailBody = MailTemplatePopulater.populateTemplateWithFoKo(mailBody, reg.getFoko());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, reg.getStudent());
            mailSenderBean.send("IWIQA: Reminder: FoKo in drei Tagen", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentReminderFoKoInThreeDays()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_Foko_Reminder_3Days.html nicht geladen werden konnte.");
        }
    }
    
    public void sendStudentReminderFoKoInOneWeek(FoKoRegistration reg){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_S_Foko_Reminder_1Week.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithFoKoRegistration(rawTemplate, reg);
            mailBody = MailTemplatePopulater.populateTemplateWithFoKo(mailBody, reg.getFoko());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, reg.getStudent());
            mailSenderBean.send("IWIQA: Reminder: FoKo in einer Woche", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentReminderFoKoInOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_Foko_Reminder_1Week.html nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorQAUploaded(QA qa){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_A_QA_Uploaded.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithQA(rawTemplate, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailSenderBean.send("IWIQA: Qualifikationsarbeit hochgeladen", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorQAUploaded()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Uploaded.html nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorReminderQAGradingOneWeek(QA qa){
        String rawTemplate = MailTemplateReader.readTemplate("Mailtemp_A_QA_Reminder_Grading_1Week.html");
        String mailBody;
        if (rawTemplate != null) {
            mailBody = MailTemplatePopulater.populateTemplateWithQA(rawTemplate, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailSenderBean.send("IWIQA: Reminder: Bewertung ausstehend", "marc.jost@digital-front.ch", mailBody); //ToDO: CHANGE
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorReminderQAGradingOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Reminder_Grading_1Week.html nicht geladen werden konnte.");
        }
    }
}
