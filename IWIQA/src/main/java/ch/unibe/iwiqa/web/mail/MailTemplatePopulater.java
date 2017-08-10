/*
 */
package ch.unibe.iwiqa.web.mail;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.Professor;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 *
 * @author Marc Jost
 */
public class MailTemplatePopulater {

    public static String populateTemplateWithStudent(String rawTemplate, Student student) {
        String mailBody = rawTemplate;
        
        if(student.getCity() != null) mailBody = mailBody.replaceAll("\\{student.city\\}", student.getCity());
        if(student.getFirstName() != null) mailBody = mailBody.replaceAll("\\{student.firstname\\}", student.getFirstName());
        if(student.getLastName() != null) mailBody = mailBody.replaceAll("\\{student.lastname\\}", student.getLastName());
        if(student.getMatNr() != null) mailBody = mailBody.replaceAll("\\{student.matnr\\}", student.getMatNr());
        if(student.getStreet() != null) mailBody = mailBody.replaceAll("\\{student.street\\}", student.getStreet());
        if(student.getZIP() != null) mailBody = mailBody.replaceAll("\\{student.zip\\}", student.getZIP());
        if(student.getFullName() != null) mailBody = mailBody.replaceAll("\\{student.fullname\\}", student.getFullName());
        if(student.getEmail() != null) mailBody = mailBody.replaceAll("\\{student.email\\}", student.getEmail());
        
        return mailBody;
    }

    public static String populateTemplateWithQA(String rawTemplate, QA qa) {
        String mailBody = rawTemplate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        if(qa.getEndingDate() != null) mailBody = mailBody.replaceAll("\\{qa.endingDate\\}", sdf.format(qa.getEndingDate()));
        if(qa.getGradedDate() != null) mailBody = mailBody.replaceAll("\\{qa.gradedDate\\}", sdf.format(qa.getGradedDate()));
        if(qa.getHandInDate() != null) mailBody = mailBody.replaceAll("\\{qa.handInDate\\}", sdf.format(qa.getHandInDate()));
        if(qa.getStartingDate() != null) mailBody = mailBody.replaceAll("\\{qa.startingDate\\}", sdf.format(qa.getStartingDate()));
        if(qa.getQaType() != null) mailBody = mailBody.replaceAll("\\{qa.qa_type.unabbreviated\\}", qa.getQaType().getUnabbreviated());
        if(qa.getQaType() != null) mailBody = mailBody.replaceAll("\\{qa.qa_type.abbreviated\\}", qa.getQaType().getAbbreviated());
        if(qa.getStatus() != null) mailBody = mailBody.replaceAll("\\{qa.qa_status\\}", ResourceBundle.getBundle("text").getString("qa_status." + qa.getStatus()));
        if(qa.getTitle() != null) mailBody = mailBody.replaceAll("\\{qa.title\\}", qa.getTitle());
        mailBody = mailBody.replaceAll("\\{qa.grade\\}", Double.toString(qa.getGrade()));
        
        return mailBody;
    }
    
    public static String populateTemplateWithFoKo(String rawTemplate, FoKo foko) {
        String mailBody = rawTemplate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        
        if(foko.getRoom() != null) mailBody = mailBody.replaceAll("\\{foko.room\\}", foko.getRoom());
        if(foko.getStartingDate() != null) mailBody = mailBody.replaceAll("\\{foko.startingDate\\}", sdf.format(foko.getStartingDate()));
        if(foko.getStartingDate() != null) mailBody = mailBody.replaceAll("\\{foko.startingDate.date\\}", sdf.format(foko.getStartingDate()));
        if(foko.getStartingDate() != null) mailBody = mailBody.replaceAll("\\{foko.startingDate.time\\}", sdfTime.format(foko.getStartingDate()));
        
        return mailBody;
    }
    
    public static String populateTemplateWithFoKoRegistration(String rawTemplate, FoKoRegistration reg) {
        String mailBody = rawTemplate;
        
        if(reg.getParticipatingAs() != null) mailBody = mailBody.replaceAll("\\{reg.participateAs\\}", ResourceBundle.getBundle("text").getString("foko_participateas." + reg.getParticipatingAs()));
        
        return mailBody;
    }
    
    public static String populateTemplateWithAdvisor(String rawTemplate, Advisor advisor) {
        String mailBody = rawTemplate;
        
        if(advisor.getEmail() != null) mailBody = mailBody.replaceAll("\\{advisor.email\\}", advisor.getEmail());
        if(advisor.getFirstName()!= null) mailBody = mailBody.replaceAll("\\{advisor.firstname\\}", advisor.getFirstName());
        if(advisor.getLastName()!= null) mailBody = mailBody.replaceAll("\\{advisor.lastname\\}", advisor.getLastName());
        if(advisor.getFullName() != null) mailBody = mailBody.replaceAll("\\{advisor.fullname\\}", advisor.getFullName());
        
        return mailBody;
    }
    
    public static String populateTemplateWithProfessor(String rawTemplate, Professor prof) {
        String mailBody = rawTemplate;
        
        if(prof.getTitles() != null) mailBody = mailBody.replaceAll("\\{prof.titles\\}", prof.getTitles());
        if(prof.getFirstName() != null) mailBody = mailBody.replaceAll("\\{prof.firstname\\}", prof.getFirstName());
        if(prof.getLastName() != null) mailBody = mailBody.replaceAll("\\{prof.lastname\\}", prof.getLastName());
        if(prof.getFullname() != null) mailBody = mailBody.replaceAll("\\{prof.fullname\\}", prof.getFullname());
        
        return mailBody;
    }
}
