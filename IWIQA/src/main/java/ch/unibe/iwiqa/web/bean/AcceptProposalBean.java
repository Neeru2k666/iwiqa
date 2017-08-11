/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.util.QA_Status;
import ch.unibe.iwiqa.web.mail.MailNotificationManagerBean;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class AcceptProposalBean {
    
    @Inject
    private QAFacade qAFacade;
    
    @Inject
    private MailNotificationManagerBean mailNotificationManagerBean;
    
    public void acceptProposal(QA qa) {
        qa.setStartingDate(new Date());
        qa.setStatus(QA_Status.PROPOSAL_ACCEPTED);
        qAFacade.edit(qa);
        mailNotificationManagerBean.sendStudentQAStatusUpdate(qa);
    }
}
