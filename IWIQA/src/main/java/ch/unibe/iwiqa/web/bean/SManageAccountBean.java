/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.StudentFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@ViewScoped
@Named (value = "SManageAccountBean")
public class SManageAccountBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    private StudentFacade studentFacade;
    
    private Student student;
    
    private boolean editMode = false;
    
    private String newPassword = "";
    
    @PostConstruct
    private void init(){
        student = (Student) SecurityUtils.getSubject().getPrincipal();
        if(student == null) return;
    }
    
    public void edit() {
        editMode = true;
    }
    
    public void editStudent(){
        studentFacade.edit(student);
        editMode = false;
        Messages.addGlobal(new FacesMessage("Änderungen erfolgreich gespeichert!"));
    }
    
    public void changePassword(){
        String hashedPW = new Sha256Hash(newPassword).toHex();
        student.setPassword(hashedPW);
        studentFacade.edit(student);
        Messages.addGlobal(new FacesMessage("Passwort erfolgreich geändert!"));
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
