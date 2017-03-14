/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.util.QA_Status;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class AbortQABean {
    
    @Inject
    private QAFacade qAFacade;
    
    public void abortQA(QA qa){
        qa.setStatus(QA_Status.QA_ABORTED);
        qAFacade.edit(qa);
    }
    
}
