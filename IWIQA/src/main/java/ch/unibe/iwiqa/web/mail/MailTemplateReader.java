/*
 */
package ch.unibe.iwiqa.web.mail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Marc Jost
 */
public class MailTemplateReader {
    
    public static String readTemplate(String templatename){
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
            
}
