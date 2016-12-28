/*
 */
package ch.unibe.iwiqa.dev;

import ch.unibe.iwiqa.entity.Advisor;
import ch.unibe.iwiqa.entity.dao.AdvisorFacade;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Marc Jost
 */
@Startup
@Singleton
public class DevelopmentTestData {
    
    @Inject
    private AdvisorFacade advisorFacade;
    
    @PostConstruct
    public void initializeData(){
        createTestAdvisors();
    }
    
    private void createTestAdvisors(){
        Advisor adv1 = new Advisor();
        adv1.setEmail("advisor1@gmail.com");
        adv1.setFirstName("AdviOne");
        adv1.setLastName("SorOne");
        
        advisorFacade.create(adv1);
        
        Advisor adv2 = new Advisor();
        adv2.setEmail("advisor2@gmail.com");
        adv2.setFirstName("AdviTwo");
        adv2.setLastName("SorTwo");
        
        advisorFacade.create(adv2);
    }
}
