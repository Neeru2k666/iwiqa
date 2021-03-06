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
import java.util.Date;
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
@Named(value = "AIndexBean")
@ViewScoped
public class AIndexBean implements Serializable {
    
    private static final long serialVersionUID = -9186749789853072367L;
    
    @Inject
    private FoKoFacade foKoFacade;
    
    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private AcceptProposalBean acceptProposalBean;
    
    @Inject
    private AbortQABean abortQABean;
    
    @Inject
    private GradeQABean gradeQABean;
    
    @Inject
    private CreateGradeAnnouncementBean createGradeAnnouncementBean;
    
    private List<QA> openQAs = new ArrayList<>();
    
    private List<FoKo> availableFoKos = new ArrayList<>();
    
    private Advisor loggedInAdvisor;
    
    private QA selectedQA;
    
    private double achievedGrade;
    
    private Date handInDate;
    
    @PostConstruct
    private void init(){
        loggedInAdvisor = (Advisor) SecurityUtils.getSubject().getPrincipal();
        if(loggedInAdvisor == null) return;
        refreshQAs();
        availableFoKos = foKoFacade.findAllInFutureOrderByDate();
    }
    
    public void acceptProposal(QA qa){
        acceptProposalBean.acceptProposal(qa);
        refreshQAs();
        Messages.addGlobal(new FacesMessage("Proposal angenommen"));
    }
    
    public void gradeQA() {
        gradeQABean.gradeQA(selectedQA, achievedGrade, handInDate);
        refreshQAs();
        Messages.addGlobal(new FacesMessage("Note erfolgreich gesetzt"));
    }
    
    public void abortQA(QA qa){
        abortQABean.abortQA(qa);
        refreshQAs();
        Messages.addGlobal(new FacesMessage("Qualifikationsarbeit abgebrochen"));
    }
    
    public void createGradeAnnouncement(QA qa){
        try {
            createGradeAnnouncementBean.create(qa);
            refreshQAs();
            Messages.addGlobal(new FacesMessage("Notenmeldung erfolgreich generiert. <a href='/IWIQA/resources/files/" + qa.getId() +"' target='_blank'>Klicken Sie hier, um sie herunterzuladen.</a>"));
        } catch (Exception e){
            Messages.addGlobal(new FacesMessage("Notenmeldung konnte nicht erstellt werden. Überprüfen Sie die Serverlogs."));
        }
    }
    
    private void refreshQAs(){
        openQAs = qAFacade.findOpenAndNotAbortedQAsByAdvisor(loggedInAdvisor);
    }

    public List<QA> getOpenQAs() {
        return openQAs;
    }

    public void setOpenQAs(List<QA> openQAs) {
        this.openQAs = openQAs;
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

    public Date getHandInDate() {
        return handInDate;
    }

    public void setHandInDate(Date handInDate) {
        this.handInDate = handInDate;
    }
}
