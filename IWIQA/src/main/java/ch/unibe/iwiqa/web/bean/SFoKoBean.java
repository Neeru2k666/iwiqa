/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import ch.unibe.iwiqa.entity.dao.FoKoRegistrationFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.entity.dao.StudentFacade;
import ch.unibe.iwiqa.util.FoKo_ParticipateAs;
import ch.unibe.iwiqa.web.util.Navigation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;

/**
 *
 * @author Marc Jost
 */
@Named(value = "sfokoBean")
@ViewScoped
public class SFoKoBean implements Serializable {

    private static final long serialVersionUID = 8258927200455896846L;

    @Inject
    private FoKoFacade foKoFacade;

    @Inject
    private FoKoRegistrationFacade foKoRegistrationFacade;

    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private StudentFacade studentFacade;
    
    @Inject
    private DeregisterFoKoBean deregisterFoKoBean;

    private Student loggedInStudent;

    private List<FoKo> availableFoKos = new ArrayList<>();

    private List<QA> myQAs;

    private List<FoKoRegistration> myFoKoRegistrations;

    private FoKo selectedFoKo;

    private QA selectedQA;
    
    private FoKo_ParticipateAs participateAs;

    @PostConstruct
    private void init() {
        loggedInStudent = (Student) SecurityUtils.getSubject().getPrincipal();
        if (loggedInStudent == null) {
            return;
        }
        myQAs = loggedInStudent.getQas();
        myFoKoRegistrations = loggedInStudent.getFokoRegistrations();
        availableFoKos = foKoFacade.findAll();
    }

    public boolean hasRegisteredForFoKo(FoKo f) {
        for (FoKoRegistration reg : myFoKoRegistrations) {
            if (reg.getFoko().equals(f)) {
                return true;
            }
        }
        return false;
    }

    public void registerToFoKo() {
        FoKoRegistration reg = new FoKoRegistration();
        reg.setFoko(selectedFoKo);
        reg.setQa(selectedQA);
        reg.setStudent(loggedInStudent);
        reg.setParticipatingAs(participateAs);

        selectedFoKo.addParticipant(reg);
        loggedInStudent.addFoKoRegistration(reg);
        selectedQA.addFoKoRegistration(reg);

        foKoRegistrationFacade.create(reg);
        foKoFacade.edit(selectedFoKo);
        studentFacade.edit(loggedInStudent);
        qAFacade.edit(selectedQA);
        
        //init();
    }
    
    public void deregister(FoKoRegistration reg){
        deregisterFoKoBean.deregisterFoKo(reg);
    }

    public List<FoKo> getAvailableFoKos() {
        return availableFoKos;
    }

    public void setAvailableFoKos(List<FoKo> availableFoKos) {
        this.availableFoKos = availableFoKos;
    }

    public List<FoKoRegistration> getMyFoKoRegistrations() {
        return myFoKoRegistrations;
    }

    public void setMyFoKoRegistrations(List<FoKoRegistration> myFoKoRegistrations) {
        this.myFoKoRegistrations = myFoKoRegistrations;
    }

    public FoKo getSelectedFoKo() {
        return selectedFoKo;
    }

    public void setSelectedFoKo(FoKo selectedFoKo) {
        this.selectedFoKo = selectedFoKo;
    }

    public QA getSelectedQA() {
        return selectedQA;
    }

    public void setSelectedQA(QA selectedQA) {
        this.selectedQA = selectedQA;
    }

    public List<QA> getMyQAs() {
        return myQAs;
    }

    public void setMyQAs(List<QA> myQAs) {
        this.myQAs = myQAs;
    }

    public FoKo_ParticipateAs getParticipateAs() {
        return participateAs;
    }

    public void setParticipateAs(FoKo_ParticipateAs participateAs) {
        this.participateAs = participateAs;
    }
}
