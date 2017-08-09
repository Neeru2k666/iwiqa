/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class NewAdvisorBean {
    
    @Inject
    private AdvisorFacade advisorFacade;
    
    private Advisor newAdvisor;
    
    @PostConstruct
    private void init(){
        newAdvisor = new Advisor();
    }
    
    public void save(){
        newAdvisor.setPassword(new Sha256Hash(newAdvisor.getPassword()).toHex());
        advisorFacade.create(newAdvisor);
        Messages.addGlobalInfo("Betreuer erstellt");
    }

    public Advisor getNewAdvisor() {
        return newAdvisor;
    }

    public void setNewAdvisor(Advisor newAdvisor) {
        this.newAdvisor = newAdvisor;
    }
}
