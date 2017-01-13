/*
 */
package ch.unibe.iwiqa.web.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 *
 * @author Marc Jost
 */
public class TargetedAuthenticationToken extends UsernamePasswordToken {
    
    private static final long serialVersionUID = 8734334333382609393L;
    
    private String target;
    
    public TargetedAuthenticationToken(String username, String password, String target){
        super(username, password, true);
        this.target = target;
    }
    
    public String getTarget(){
        return target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }
}
