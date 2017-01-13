/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named
@ViewScoped
public class AdvisorIndexBean implements Serializable {
    
    private static final long serialVersionUID = -9186749789853072367L;
    
    @Inject
    private FoKoFacade foKoFacade;
    
    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private AcceptProposalBean acceptProposalBean;
    
    @Inject
    private GradeQABean gradeQABean;
    
    private List<QA> myQAs = new ArrayList<>();
    
    private List<FoKo> availableFoKos = new ArrayList<>();
    
    private Advisor loggedInAdvisor;
    
    private QA selectedQA;
    
    private double achievedGrade;
    
    @PostConstruct
    private void init(){
        loggedInAdvisor = (Advisor) SecurityUtils.getSubject().getPrincipal();
        if(loggedInAdvisor == null) return;
        myQAs = loggedInAdvisor.getQas();
        availableFoKos = foKoFacade.findAll();
    }
    
    public void acceptProposal(QA qa){
        acceptProposalBean.acceptProposal(qa);
        myQAs = loggedInAdvisor.getQas();
        Messages.addGlobal(new FacesMessage("Proposal angenommen"));
    }
    
    public void gradeQA() {
        gradeQABean.gradeQA(selectedQA, achievedGrade);
        myQAs = loggedInAdvisor.getQas();
        Messages.addGlobal(new FacesMessage("Note erfolgreich gesetzt"));
    }

    public List<QA> getMyQAs() {
        return myQAs;
    }

    public void setMyQAs(List<QA> myQAs) {
        this.myQAs = myQAs;
    }

    public List<FoKo> getAvailableFoKos() {
        return availableFoKos;
    }

    public void setAvailableFoKos(List<FoKo> availableFoKos) {
        this.availableFoKos = availableFoKos;
    }

    public void setSelectedQA(QA selectedQA) {
        this.selectedQA = selectedQA;
    }

    public void setAchievedGrade(double achievedGrade) {
        this.achievedGrade = achievedGrade;
    }

    public double getAchievedGrade() {
        return achievedGrade;
    }
}
