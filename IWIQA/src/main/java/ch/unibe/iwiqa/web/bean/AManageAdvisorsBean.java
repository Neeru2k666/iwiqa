/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    
    private List<Advisor> advisors = new ArrayList<>();
    
    private boolean editMode = false;
    
    @PostConstruct
    private void init(){
        advisors = advisorFacade.findAll();
    }
    
    public void edit(){
        editMode = true;
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
}
