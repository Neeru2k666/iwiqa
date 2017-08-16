/*
 */
package ch.unibe.iwiqa.web.mail;

import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.IWIQASettings;
import ch.unibe.iwiqa.entity.MailTemplate;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.IWIQASettingsFacade;
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
    
    @Inject
    private MailTemplateReader mailTemplateReader;
    
    @Inject
    private IWIQASettingsFacade iWIQASettingsFacade;

    public void sendStudentRegistrationSuccess(Student student) {
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_S_Regsuccess");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, student);
            mailSenderBean.send(mt.getSubject(), student.getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentRegistrationSuccess()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_Regsuccess nicht geladen werden konnte.");
        }
    }

    public void sendStudentQAStatusUpdate(QA qa) {
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_S_QA_Status_Update");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailSenderBean.send(mt.getSubject(), qa.getStudent().getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentQAStatusUpdate()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_QA_Status_Update nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorQAGraded(QA qa) {
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_A_QA_Graded");
        if (mt != null) {
            IWIQASettings settings = iWIQASettingsFacade.findAll().get(0);
            String gradeAnnouncerEmail = settings != null ? settings.getGradeAnnouncerEmail() : qa.getAdvisor().getEmail();
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailSenderBean.send(mt.getSubject(), gradeAnnouncerEmail, mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorQAGraded()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Graded nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorNewQA(QA qa) {
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_A_QA_New");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailSenderBean.send(mt.getSubject(), qa.getAdvisor().getEmail(), mailBody); 
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorNewQA()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_New nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorReminderQAEndingInOneWeek(QA qa){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_A_QA_Reminder_Endingdate_1Week");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailSenderBean.send(mt.getSubject(), qa.getAdvisor().getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorReminderQAEndingInOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Reminder_Endingdate_1Week nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorReminderQAGradeAnnouncementOneWeek(QA qa){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_A_QA_Reminder_GradeAnnouncement_1Week");
        if (mt != null) {
            IWIQASettings settings = iWIQASettingsFacade.findAll().get(0);
            String gradeAnnouncerEmail = settings != null ? settings.getGradeAnnouncerEmail() : qa.getAdvisor().getEmail();
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, qa);
            mailSenderBean.send(mt.getSubject(), gradeAnnouncerEmail, mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorReminderQAGradeAnnouncementOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Reminder_GradeAnnouncement_1Week nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorFoKoRegistration(FoKoRegistration reg){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_A_Foko_Registration");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithFoKoRegistration(mailBody, reg);
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, reg.getQa());
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, reg.getQa().getAdvisor());
            mailBody = MailTemplatePopulater.populateTemplateWithFoKo(mailBody, reg.getFoko());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, reg.getStudent());
            mailSenderBean.send(mt.getSubject(), reg.getFoko().getResponsibleAdvisor().getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorFoKoRegistration()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_Foko_Registration nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorFoKoRegistrationCancelled(FoKoRegistration reg){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_A_Foko_Registration_Cancelled");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithFoKoRegistration(mailBody, reg);
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, reg.getQa());
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, reg.getQa().getAdvisor());
            mailBody = MailTemplatePopulater.populateTemplateWithFoKo(mailBody, reg.getFoko());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, reg.getStudent());
            mailSenderBean.send(mt.getSubject(), reg.getFoko().getResponsibleAdvisor().getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorFoKoRegistrationCancelled()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_Foko_Registration_Cancelled nicht geladen werden konnte.");
        }
    }
    
    public void sendStudentReminderQAEndingInOneWeek(QA qa){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_S_QA_Reminder_Endingdate_1Week");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailSenderBean.send(mt.getSubject(), qa.getStudent().getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentReminderQAEndingInOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_QA_Reminder_Endingdate_1Week nicht geladen werden konnte.");
        }
    }
    
    public void sendStudentReminderFoKoInThreeDays(FoKoRegistration reg){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_S_Foko_Reminder_3Days");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithFoKoRegistration(mailBody, reg);
            mailBody = MailTemplatePopulater.populateTemplateWithFoKo(mailBody, reg.getFoko());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, reg.getStudent());
            mailSenderBean.send(mt.getSubject(), reg.getStudent().getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentReminderFoKoInThreeDays()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_Foko_Reminder_3Days nicht geladen werden konnte.");
        }
    }
    
    public void sendStudentReminderFoKoInOneWeek(FoKo foko, Student student){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_S_Foko_Reminder_1Week");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithFoKo(mailBody, foko);
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, student);
            mailSenderBean.send(mt.getSubject(), student.getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendStudentReminderFoKoInOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_S_Foko_Reminder_1Week nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorQAUploaded(QA qa){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_A_QA_Uploaded");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailBody = MailTemplatePopulater.populateTemplateWithStudent(mailBody, qa.getStudent());
            mailSenderBean.send(mt.getSubject(), qa.getAdvisor().getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorQAUploaded()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Uploaded nicht geladen werden konnte.");
        }
    }
    
    public void sendAdvisorReminderQAGradingOneWeek(QA qa){
        MailTemplate mt = mailTemplateReader.readTemplateFromDB("Mailtemp_A_QA_Reminder_Grading_1Week");
        if (mt != null) {
            String mailBody = mt.getMailBody();
            mailBody = MailTemplatePopulater.populateTemplateWithQA(mailBody, qa);
            mailBody = MailTemplatePopulater.populateTemplateWithAdvisor(mailBody, qa.getAdvisor());
            mailSenderBean.send(mt.getSubject(), qa.getAdvisor().getEmail(), mailBody);
        } else {
            System.out.println("ch.unibe.iwiqa.web.mail.MailNotificationManagerBean.sendAdvisorReminderQAGradingOneWeek()");
            System.out.println("Email konnte nicht verschickt werden, weil das Email Template Mailtemp_A_QA_Reminder_Grading_1Week nicht geladen werden konnte.");
        }
    }
}
