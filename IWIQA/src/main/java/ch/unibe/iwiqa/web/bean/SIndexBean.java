/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.FoKoRegistrationFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author Marc Jost
 */
@Named (value = "SIndexBean")
@ViewScoped
public class SIndexBean implements Serializable {

    private static final long serialVersionUID = -8014037414719166347L;
    
    @Inject
    private QAFacade qaFacade;
    
    @Inject
    private FoKoRegistrationFacade foKoRegistrationFacade;
    
    private List<QA> myQAs = new ArrayList<>();
    
    private List<FoKoRegistration> myFoKoRegistrations = new ArrayList<>();
    
    private Student loggedInStudent;
    
    @PostConstruct
    private void init() {
        loggedInStudent = (Student) SecurityUtils.getSubject().getPrincipal();
        if(loggedInStudent == null) {
            return;
        }
        myQAs = qaFacade.findAllByStudent(loggedInStudent);
        myFoKoRegistrations = loggedInStudent.getFokoRegistrations();
    }

    public List<QA> getMyQAs() {
        return myQAs;
    }

    public void setMyQAs(List<QA> myQAs) {
        this.myQAs = myQAs;
    }

    public List<FoKoRegistration> getMyFoKoRegistrations() {
        return myFoKoRegistrations;
    }

    public void setMyFoKoRegistrations(List<FoKoRegistration> myFoKoRegistrations) {
        this.myFoKoRegistrations = myFoKoRegistrations;
    }

    public Student getLoggedInStudent() {
        return loggedInStudent;
    }

    public void setLoggedInStudent(Student loggedInStudent) {
        this.loggedInStudent = loggedInStudent;
    }
    
    
}
