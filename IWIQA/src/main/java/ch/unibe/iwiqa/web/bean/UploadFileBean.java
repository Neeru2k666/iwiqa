/*
 */
package ch.unibe.iwiqa.web.bean;

import ch.unibe.iwiqa.entity.QA;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

/**
 *
 * @author Marc Jost
 */
@RequestScoped
public class UploadFileBean {

    private Part file;

    private String fileContent;

    public void upload(Part file, QA qa) {
        try (InputStream input = file.getInputStream()) {
            Files.copy(input, new File("S:\\up", generateFilename(qa)).toPath());
        } catch (IOException e) {
            // Error handling
        }
    }

    private String generateFilename(QA qa) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return "" + sdf.format(new Date()) + "_" + qa.getQaType().getAbbreviated() + "_" + qa.getStudent().getLastName() + "_" + qa.getStudent().getFirstName() + ".pdf";
    }
}
