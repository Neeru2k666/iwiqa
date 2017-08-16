/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
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
    
    private Advisor loggedInAdvisor;
    
    @PostConstruct
    private void init(){
        loggedInAdvisor = (Advisor) SecurityUtils.getSubject().getPrincipal();
        if(loggedInAdvisor == null) return;
        newFoKo = new FoKo();
    }
    
    public void save(){
        newFoKo.setResponsibleAdvisor(loggedInAdvisor);
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
