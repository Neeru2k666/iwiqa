/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.web.security.TargetedAuthenticationToken;
import ch.unibe.iwiqa.web.util.Navigation;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author Marc Jost
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = -8679256432908991345L;
    
    private String username;
    private String password;
    private String target = "student"; // default value for switch box
    
    /**
     * Establishes userSession due to given userinput.
     * @throws IOException if given userinput does not match username
     * or password in the database.
     */
    public void login() throws IOException {
        try {
            SecurityUtils.getSubject().login(new TargetedAuthenticationToken(username, password, target));
            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
            String index = target.equals("student") ? Navigation.SINDEX : Navigation.AINDEX;
            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : index);
        } catch (AuthenticationException ex) {
            Messages.addGlobalError("Unbekannter Benutzer, bitte versuchen Sie es nochmals.");
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Destroys userSession and navigates the user back to the indexpage.
     * @throws IOException 
     */
     public void logout() throws IOException {
        SecurityUtils.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect(Navigation.INDEX);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
    public void setSwitchBoxTarget(ValueChangeEvent e) {
        target = ((Boolean) e.getNewValue()) ? "advisor" : "student";
    }
}
