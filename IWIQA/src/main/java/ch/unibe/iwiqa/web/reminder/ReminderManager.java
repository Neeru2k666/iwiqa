/*
 */
package ch.unibe.iwiqa.web.reminder;

import ch.unibe.iwiqa.entity.FoKo;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.FoKoFacade;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import ch.unibe.iwiqa.web.mail.MailNotificationManagerBean;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author Marc Jost
 */
@Singleton
public class ReminderManager {

    @Inject
    private MailNotificationManagerBean mailNotificationManagerBean;

    @Inject
    private FoKoFacade foKoFacade;

    @Inject
    private QAFacade qAFacade;

    @Schedule(persistent = false) // Scheduled every day at midnight
    public void remindQAEndingOneWeek() {
        List<QA> openQAs = qAFacade.findOpenUnremindedOfEndingInOneWeekQAs();

        // Need to compare dates manually, because the development DB Derby 
        // is a piece of shit and doesn't support comprehensive date functions.
        // Using Derby stuff would potentially jeopardize the production DB,
        // So we're doing this by hand. FML
        for (QA qa : openQAs) {
            long diff = qa.getEndingDate().getTime() - new Date().getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (7 >= diffDays && diffDays >= 0) {
                mailNotificationManagerBean.sendAdvisorReminderQAEndingInOneWeek(qa);
                mailNotificationManagerBean.sendStudentReminderQAEndingInOneWeek(qa);
                qa.setReceivedReminderEndingOneWeek(true);
                qAFacade.edit(qa);
            }
        }
    }

    @Schedule(dayOfWeek = "Mon", hour="0", persistent = false) // TODO CHANGE
    public void remindAdvisorGradeAnnouncementOneWeek() {
        // Schedule once every week
    }

    @Schedule(dayOfWeek = "Mon", hour="0", persistent = false) // TODO CHANGE AND ADD TRY CATCH
    public void remindAdvisorGradingOneWeek() {
        // Schedule once every week
        List<QA> ungradedQAs = qAFacade.findHandedInQAs();
        for (QA qa : ungradedQAs) {
            if (qa.getHandInDate() != null) {
                long diff = new Date().getTime() - qa.getHandInDate().getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);
                if (diffDays >= 7) {
                    mailNotificationManagerBean.sendAdvisorReminderQAGradingOneWeek(qa);
                }
            }
        }
    }

    @Schedule(hour = "*", minute = "*/1", second = "0", persistent = false) // TODO CHANGE
    public void remindStudentFoKoOneWeek() {
        List<FoKo> unremindedOpenFoKos = foKoFacade.findUnremindedInFutureFoKos();
        if (!unremindedOpenFoKos.isEmpty()) {
            List<QA> openQAs = qAFacade.findAllOpenAndNotAbortedQAs();

            for (FoKo foko : unremindedOpenFoKos) {
                long diff = foko.getStartingDate().getTime() - new Date().getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);
                if (7 >= diffDays && diffDays >= 0) {
                    boolean sentOutMailToStudent = false;
                    for (QA qa : openQAs) {
                        mailNotificationManagerBean.sendStudentReminderFoKoInOneWeek(foko, qa.getStudent());
                        sentOutMailToStudent = true;
                    }
                    if (sentOutMailToStudent) {
                        foko.setSentOutReminderOneWeek(true);
                        foKoFacade.edit(foko);
                    }
                }
            }
        }

    }

    @Schedule(hour = "*", minute = "*/1", second = "0", persistent = false) // TODO CHANGE
    public void remindStudentFoKoThreeDays() {

    }

}
