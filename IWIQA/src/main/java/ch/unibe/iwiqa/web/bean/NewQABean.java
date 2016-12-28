/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.util.QA_Type;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    
    private QA newQA;
    
    private List<Advisor> availableAdvisors;
    
    private Advisor selectedAdvisor;
    
    
    @PostConstruct
    private void init(){
        newQA = new QA();
        availableAdvisors = advisorFacade.findAll();
    }
    
    public void registerNewQA(){
        newQA.setAdvisor(selectedAdvisor);
        qaFacade.create(newQA);
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
