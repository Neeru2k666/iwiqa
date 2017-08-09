/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.Professor;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import ch.unibe.iwiqa.entity.dao.ProfessorFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named (value = "aManageAdvisorsBean")
@ViewScoped
public class AManageAdvisorsBean implements Serializable {

    private static final long serialVersionUID = -3790995610407219596L;
    
    @Inject
    private AdvisorFacade advisorFacade;
    
    @Inject
    private ProfessorFacade professorFacade;
    
    private List<Advisor> advisors = new ArrayList<>();
    
    private List<Professor> professors = new ArrayList<>();
    
    private boolean editModeAdvisors = false;
    
    private boolean editModeProfessors = false;
    
    @PostConstruct
    private void init(){
        advisors = advisorFacade.findAll();
        professors = professorFacade.findAll();
    }
    
    public void editAdvisorMode(){
        editModeAdvisors = true;
    }
    
    public void editProfessorMode(){
        editModeProfessors = true;
    }
    
    public void editAdvisors(){
        try {
            for (Advisor a : advisors) {
                advisorFacade.edit(a);
            }
            editModeAdvisors = false;
            Messages.addGlobalInfo("Änderungen gespeichert");
        } catch (Exception e) {
            Messages.addGlobalError("Änderungen konnten nicht gespeichert werden - versuchen Sie es nochmals");
        }
    }
    
    public void editProfessors(){
        try {
            for (Professor p : professors) {
                professorFacade.edit(p);
            }
            editModeProfessors = false;
            Messages.addGlobalInfo("Änderungen gespeichert");
        } catch (Exception e) {
            Messages.addGlobalError("Änderungen konnten nicht gespeichert werden - versuchen Sie es nochmals");
        }
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public boolean isEditModeAdvisors() {
        return editModeAdvisors;
    }

    public void setEditModeAdvisors(boolean editModeAdvisors) {
        this.editModeAdvisors = editModeAdvisors;
    }

    public boolean isEditModeProfessors() {
        return editModeProfessors;
    }

    public void setEditModeProfessors(boolean editModeProfessors) {
        this.editModeProfessors = editModeProfessors;
    }
    
}
