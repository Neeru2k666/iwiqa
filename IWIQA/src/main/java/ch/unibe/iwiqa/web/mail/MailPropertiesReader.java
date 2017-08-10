/*
 */
package ch.unibe.iwiqa.web.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Marc Jost
 */
public class MailPropertiesReader {

    public static Properties readProperties() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream("mail.properties");

            Properties properties = new Properties();
            properties.load(input);
            
            return properties;
        } catch (IOException e) {
            System.err.println("Could not load Mail.Properties");
            return null;
        }
    }
}
