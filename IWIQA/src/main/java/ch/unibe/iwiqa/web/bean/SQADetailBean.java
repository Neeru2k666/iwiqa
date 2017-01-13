/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named(value = "sqaDetailBean")
@ViewScoped
public class SQADetailBean implements Serializable {

    private static final long serialVersionUID = 5699767358050536905L;
    
    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private AdvisorFacade advisorFacade;
    
    private QA qa;
    
    private List<Advisor> availableAdvisors = new ArrayList<>();
    
    
    public void init(){
        availableAdvisors = advisorFacade.findAll();
    }
    
    public void editQA(){
        try {
            qAFacade.edit(qa);
            Messages.addGlobalInfo("Änderungen an der QA gespeichert");
        } catch (Exception e) {
            Messages.addGlobalError("Änderungen an der QA konnten nicht gespeichert werden - versuchen Sie es nochmals");
        }
        
    }
    
    public QA getQa() {
        return qa;
    }

    public void setQa(QA qa) {
        this.qa = qa;
    }

    public List<Advisor> getAvailableAdvisors() {
        return availableAdvisors;
    }

    public void setAvailableAdvisors(List<Advisor> availableAdvisors) {
        this.availableAdvisors = availableAdvisors;
    }
}
