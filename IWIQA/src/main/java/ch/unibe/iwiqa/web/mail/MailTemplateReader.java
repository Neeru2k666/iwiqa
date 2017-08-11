/*
 */
package ch.unibe.iwiqa.web.mail;

import ch.unibe.iwiqa.entity.MailTemplate;
import ch.unibe.iwiqa.entity.dao.MailTemplateFacade;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.inject.Inject;

/**
 * 
 * 
 * @author Marc Jost
 */
public class MailTemplateReader {
    
    @Inject
    private MailTemplateFacade mailTemplateFacade;
    
    public String readTemplateFromFiles(String templatename){
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL res = classLoader.getResource("mailtemplates/" + templatename);
            File f = new File(res.toURI());
            byte[] encoded = Files.readAllBytes(Paths.get(f.getPath()));
            return new String(encoded, "UTF-8");
        } catch (Exception e) {
            System.err.println("Could not load Mail Template");
            return null;
        }
    }
    
    public MailTemplate readTemplateFromDB(String templateName){
        return mailTemplateFacade.findByTemplateName(templateName).get(0);
    }
            
}
