/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.entity.dao.StudentFacade;
import ch.unibe.iwiqa.util.QA_Type;
import ch.unibe.iwiqa.web.mail.MailNotificationManagerBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class NewQABean {
    
    @Inject
    private QAFacade qaFacade;
    
    @Inject
    private AdvisorFacade advisorFacade;
    
    @Inject
    private StudentFacade studentFacade;
    
    @Inject
    private MailNotificationManagerBean mailNotificationManagerBean;
    
    private QA newQA;
    
    private List<Advisor> availableAdvisors;
    
    private Advisor selectedAdvisor;
    
    private Student loggedInStudent;
    
    
    @PostConstruct
    private void init(){
        loggedInStudent = (Student) SecurityUtils.getSubject().getPrincipal();
        if(loggedInStudent == null) return;
        newQA = new QA();
        availableAdvisors = advisorFacade.findAllActiveAdvisors();
    }
    
    public void registerNewQA(){
        newQA.setAdvisor(selectedAdvisor);
        newQA.setStudent(loggedInStudent);
        qaFacade.create(newQA);
        
        selectedAdvisor.addQA(newQA);
        advisorFacade.edit(selectedAdvisor);
        
        loggedInStudent.addQA(newQA);
        studentFacade.edit(loggedInStudent);
        mailNotificationManagerBean.sendAdvisorNewQA(newQA);
        Messages.addGlobal(new FacesMessage("Qualifikationsarbeit erfolgreich erstellt!"));
    }
    
    public List<Advisor> getAvailableAdvisors(){
        return availableAdvisors;
    }
    
    public QA_Type[] getTypes(){
        return QA_Type.values();
    }

    public QA getNewQA() {
        return newQA;
    }

    public void setNewQA(QA newQA) {
        this.newQA = newQA;
    }
    
    public Advisor getSelectedAdvisor(){
        return selectedAdvisor;
    }
    
    public void setSelectedAdvisor(Advisor selected){
        this.selectedAdvisor = selected;
    }
}
