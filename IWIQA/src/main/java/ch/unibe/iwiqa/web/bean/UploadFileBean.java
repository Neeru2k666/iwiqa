/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.IWIQASettings;
import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.IWIQASettingsFacade;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

/**
 *
 * @author Marc Jost
 */
@RequestScoped
public class UploadFileBean {

    @Inject
    private IWIQASettingsFacade iWIQASettingsFacade;

    public void upload(Part file, QA qa) {
        try (InputStream input = file.getInputStream()) {
            IWIQASettings settings = iWIQASettingsFacade.findAll().get(0);
            if (settings == null) {
                throw new Exception("IWIQASettings could not be loaded from DB.");
            }
            Files.copy(input, new File(settings.getPathQAFileUploadFolder(), generateFilename(qa)).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateFilename(QA qa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return "" + sdf.format(new Date()) + "_" + qa.getQaType().getAbbreviated() + "_" + qa.getStudent().getLastName() + "_" + qa.getStudent().getFirstName() + ".pdf";
    }
}
