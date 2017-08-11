/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.StudentFacade;
import ch.unibe.iwiqa.web.mail.MailNotificationManagerBean;
import ch.unibe.iwiqa.web.mail.MailSenderBean;
import ch.unibe.iwiqa.web.util.Navigation;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class RegisterStudentBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Student newStudent;
    
    @Inject
    private StudentFacade studentFacade;
    
    @Inject
    private MailNotificationManagerBean mailNotificationManagerBean;
    
    private boolean agreed = false;
    
    @PostConstruct
    private void initialize(){
        newStudent = new Student();
    }
    
    public String registerStudent(){
        newStudent.setPassword(new Sha256Hash(newStudent.getPassword()).toHex());
        studentFacade.create(newStudent);
        mailNotificationManagerBean.sendStudentRegistrationSuccess(newStudent);
        return Navigation.REGSUCCESS;
    }

    public Student getNewStudent() {
        return newStudent;
    }

    public void setNewStudent(Student newStudent) {
        this.newStudent = newStudent;
    }

    public boolean isAgreed() {
        return agreed;
    }

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }
}
