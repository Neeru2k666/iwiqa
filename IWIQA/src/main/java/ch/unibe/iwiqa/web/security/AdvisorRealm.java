/*
 */
package ch.unibe.iwiqa.web.security;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.omnifaces.config.BeanManager;

/**
 *
 * @author Marc Jost
 */
public class AdvisorRealm extends AuthenticatingRealm {
    
    private AdvisorFacade advisorFacade;

    @Override
    public boolean supports(AuthenticationToken token) {
        if(token instanceof TargetedAuthenticationToken){
            return ((TargetedAuthenticationToken) token).getTarget().equals("advisor");
        }
        return false;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        
        advisorFacade = BeanManager.INSTANCE.getReference(AdvisorFacade.class);
        Advisor advisor = advisorFacade.findByEmail(username).get(0);
        if(advisor == null) throw new AuthenticationException("Benutzer nicht gefunden");
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(advisor, advisor.getPassword(), getName());
        return info;
    }  
}
