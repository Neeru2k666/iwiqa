/*
 */
package ch.unibe.iwiqa.web.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 *
 * @author Marc Jost
 */
public class TargetedAuthenticatingFilter extends FormAuthenticationFilter {
    
    private String target;

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        return new TargetedAuthenticationToken(getUsername(request), getPassword(request), target);
    }
    
    public void setTarget(String target){
        this.target = target;
    }
    
    public String getTarget(){
        return target;
    }
}
