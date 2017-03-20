/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 *
 * @author Marc Jost
 */
@Named
@ViewScoped
public class ChangePasswordBean implements Serializable {

    private static final long serialVersionUID = -8960784085462004260L;

    @Inject
    private AdvisorFacade advisorFacade;
    
    private Advisor selectedAdvisor;
    
    private String newPassword;
    
    public void changePassword(){
        String hashedPW = new Sha256Hash(newPassword, null, 1024).toHex();
        selectedAdvisor.setPassword(hashedPW);
        advisorFacade.edit(selectedAdvisor);
    }

    public Advisor getSelectedAdvisor() {
        return selectedAdvisor;
    }

    public void setSelectedAdvisor(Advisor selectedAdvisor) {
        this.selectedAdvisor = selectedAdvisor;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    
}
