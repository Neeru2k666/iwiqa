/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
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
public class NewFoKoBean {
    
    @Inject
    private FoKoFacade foKoFacade;
    
    private FoKo newFoKo;
    
    @PostConstruct
    private void init(){
        newFoKo = new FoKo();
    }
    
    public void save(){
        foKoFacade.create(newFoKo);
        Messages.addGlobalInfo("Forschungskolloquium erstellt");
    }

    public FoKo getNewFoKo() {
        return newFoKo;
    }

    public void setNewFoKo(FoKo newFoKo) {
        this.newFoKo = newFoKo;
    }
    
}
