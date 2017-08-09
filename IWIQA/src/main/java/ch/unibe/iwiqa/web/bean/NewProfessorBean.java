/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Professor;
import ch.unibe.iwiqa.entity.dao.ProfessorFacade;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class NewProfessorBean {
    
    @Inject
    private ProfessorFacade professorFacade;
    
    private Professor newProfessor;
    
    @PostConstruct
    private void init(){
        newProfessor = new Professor();
    }
    
    public void save(){
        professorFacade.create(newProfessor);
         Messages.addGlobalInfo("Professor erstellt");
    }

    public Professor getNewProfessor() {
        return newProfessor;
    }

    public void setNewProfessor(Professor newProfessor) {
        this.newProfessor = newProfessor;
    }
}
