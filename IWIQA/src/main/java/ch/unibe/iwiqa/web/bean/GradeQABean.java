/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.util.QA_Status;
import ch.unibe.iwiqa.web.mail.MailNotificationManagerBean;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class GradeQABean {

    @Inject
    private QAFacade qAFacade;
    @Inject
    private MailNotificationManagerBean mailNotificationManagerBean;
    
    private QA selectedQA;
    private double achievedGrade = 0;

    public void gradeQA() {
        selectedQA.setGrade(achievedGrade);
        qAFacade.edit(selectedQA);
    }
    
    public void gradeQA(QA qa) {
        gradeQA(qa, qa.getGrade(), qa.getHandInDate());
    }
    
    public void gradeQA(QA qa, double grade, Date handInDate) {
        qa.setGrade(grade);
        qa.setGradedDate(new Date());
        qa.setHandInDate(handInDate);
        qa.setStatus(QA_Status.QA_GRADED);
        qAFacade.edit(qa);
        mailNotificationManagerBean.sendStudentQAStatusUpdate(qa);
        mailNotificationManagerBean.sendAdvisorQAGraded(qa);
    }

    public QA getSelectedQA() {
        return selectedQA;
    }

    public void setSelectedQA(QA selectedQA) {
        this.selectedQA = selectedQA;
    }

    public double getAchievedGrade() {
        return achievedGrade;
    }

    public void setAchievedGrade(double achievedGrade) {
        this.achievedGrade = achievedGrade;
    }

    
}
