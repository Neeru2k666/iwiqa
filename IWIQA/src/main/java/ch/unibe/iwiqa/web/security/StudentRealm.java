/*
 */
package ch.unibe.iwiqa.web.security;

import ch.unibe.iwiqa.entity.Student;
import ch.unibe.iwiqa.entity.dao.StudentFacade;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.omnifaces.config.BeanManager;

/**
 *
 * @author Marc Jost
 */
public class StudentRealm extends AuthenticatingRealm {
    
    private StudentFacade studentFacade;

    @Override
    public boolean supports(AuthenticationToken token) {
        if(token instanceof TargetedAuthenticationToken){
            return ((TargetedAuthenticationToken) token).getTarget().equals("student");
        }
        return false;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        
        studentFacade = BeanManager.INSTANCE.getReference(StudentFacade.class);
        Student student = studentFacade.findByEmail(username).get(0);
        if(student == null) throw new AuthenticationException("Benutzer nicht gefunden");
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(student, student.getPassword(), getName());
        return info;
    }  
}
