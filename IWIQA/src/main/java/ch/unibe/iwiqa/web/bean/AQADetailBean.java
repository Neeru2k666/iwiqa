/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.util.QA_Type;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named(value = "aqaDetailBean")
@ViewScoped
public class AQADetailBean implements Serializable {

    private static final long serialVersionUID = 2777298899587543418L;

    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private AdvisorFacade advisorFacade;
    
    @Inject
    private AcceptProposalBean acceptProposalBean;
    
    @Inject
    private GradeQABean gradeQABean;
    
    @Inject
    private CreateGradeAnnouncementBean createGradeAnnouncementBean;
    
    private QA qa;
    
    private List<Advisor> availableAdvisors = new ArrayList<>();
    
    private boolean editMode = false;
    
    /*private String startingDate;
    
    private String endingDate;
    
    private String handInDate;
    
    private String gradedDate;*/
    
    public void init(){
        availableAdvisors = advisorFacade.findAllActiveAdvisors();
    }
    
    public void editQA(){
        try {
            qAFacade.edit(qa);
            editMode = false;
            Messages.addGlobalInfo("Änderungen gespeichert");
        } catch (Exception e) {
            Messages.addGlobalError("Änderungen konnten nicht gespeichert werden - versuchen Sie es nochmals");
        }
    }
    
    public void acceptProposal(){
        acceptProposalBean.acceptProposal(qa);
        Messages.addGlobal(new FacesMessage("Proposal angenommen"));
    }
    
    public void gradeQA() {
        gradeQABean.gradeQA(qa);
        Messages.addGlobal(new FacesMessage("Note erfolgreich gesetzt"));
    }
    
    public void createGradeAnnouncement(){
        try {
            createGradeAnnouncementBean.create(qa);
            Messages.addGlobal(new FacesMessage("Notenmeldung erfolgreich generiert. <a href='/IWIQA/resources/files/" + qa.getId() +"' target='_blank'>Klicken Sie hier, um sie herunterzuladen.</a>"));
        } catch (Exception e){
            Messages.addGlobalError("Notenmeldung konnte nicht erstellt werden. Überprüfen Sie die Serverlogs.");
        }
    }
    
    public void edit(){
        editMode = true;
    }
    
    /*private Date formatStringToDate(String dateToBeFormatted){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return sdf.parse(dateToBeFormatted);
        } catch (ParseException ex) {
            Logger.getLogger(AQADetailBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private String formatDateToString(Date dateToBeFormatted){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(dateToBeFormatted);
    }*/
    
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

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
    public QA_Type[] getTypes(){
        return QA_Type.values();
    }

    /*public String getGradedDate() {
        return formatDateToString(qa.getGradedDate());
    }

    public void setGradedDate(String gradedDate) {
        qa.setGradedDate(formatStringToDate(gradedDate));
    }

    public String getHandInDate() {
        return formatDateToString(qa.getHandInDate());
    }

    public void setHandInDate(String handInDate) {
        qa.setHandInDate(formatStringToDate(handInDate));
    }

    public String getStartingDate() {
        return formatDateToString(qa.getStartingDate());
    }

    public void setStartingDate(String startingDate) {
        qa.setStartingDate(formatStringToDate(startingDate));
    }

    public String getEndingDate() {
        return formatDateToString(qa.getEndingDate());
    }

    public void setEndingDate(String endingDate) {
        qa.setEndingDate(formatStringToDate(endingDate));
    }*/
}
