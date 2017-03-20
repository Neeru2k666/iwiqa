/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.FoKoRegistration;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named(value = "afokoDetailBean")
@ViewScoped 
public class AFoKoDetailBean implements Serializable {
    
    private static final long serialVersionUID = -5901773936325064730L;
    
    @Inject
    private FoKoFacade foKoFacade;
    
    @Inject
    private DeregisterFoKoBean deregisterFoKoBean;
    
    private FoKo foko;
    
    private boolean editMode = false;
    
    @PostConstruct
    public void init() {
        
    }
    
    public void editFoKo(){
        try {
            foKoFacade.edit(foko);
            editMode = false;
            Messages.addGlobalInfo("Änderungen gespeichert");
        } catch (Exception e) {
            Messages.addGlobalError("Änderungen konnten nicht gespeichert werden - versuchen Sie es nochmals");
        }
    }
    
    public void edit(){
        editMode = true;
    }
    
    public void removeParticipant(FoKoRegistration par){
        deregisterFoKoBean.deregisterFoKo(par);
    }

    public FoKo getFoko() {
        return foko;
    }

    public void setFoko(FoKo foko) {
        this.foko = foko;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
}
