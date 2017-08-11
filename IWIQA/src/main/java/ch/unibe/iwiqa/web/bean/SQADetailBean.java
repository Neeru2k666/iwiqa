/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.util.QA_Status;
import ch.unibe.iwiqa.util.QA_Type;
import ch.unibe.iwiqa.web.mail.MailNotificationManagerBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.apache.shiro.SecurityUtils;
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
    
    @Inject
    private UploadFileBean uploadFileBean;
    
    @Inject
    private MailNotificationManagerBean mailNotificationManagerBean;
    
    private QA qa;
    
    private List<Advisor> availableAdvisors = new ArrayList<>();
    
    private boolean editMode= false;
    
    private Student loggedInStudent;
    
    private boolean myQA;
    
    private Part file;
    
    
    public void init(){
        availableAdvisors = advisorFacade.findAll();
        loggedInStudent = (Student) SecurityUtils.getSubject().getPrincipal();
        if(loggedInStudent == null) return;
        myQA = qa.getStudent().equals(loggedInStudent);
    }
    
    public void editQA(){
        try {
            qAFacade.edit(qa);
            Messages.addGlobalInfo("Änderungen an der QA gespeichert");
            editMode = false;
        } catch (Exception e) {
            Messages.addGlobalError("Änderungen an der QA konnten nicht gespeichert werden - versuchen Sie es nochmals");
        }
    }
    
    public void edit(){
        editMode = true;
    }
    
    public void uploadQA(){
        uploadFileBean.upload(file, qa);
        qa.setStatus(QA_Status.QA_HANDED_IN);
        qAFacade.edit(qa);
        mailNotificationManagerBean.sendStudentQAStatusUpdate(qa);
        mailNotificationManagerBean.sendAdvisorQAUploaded(qa);
        Messages.addGlobal(new FacesMessage("Qualifikationsarbeit erfolgreich hochgeladen!"));
    }
    
    public QA_Type[] getTypes(){
        return QA_Type.values();
    }

    public boolean isMyQA() {
        return myQA;
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

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
