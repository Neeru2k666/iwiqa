/*
 */
package ch.unibe.iwiqa.web.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Marc Jost
 */
@Named
@RequestScoped
public class UploadFileBean {
    
    private Part file;
    
    private String fileContent;
    
    public void upload(){
        try (InputStream input = file.getInputStream()){
            Files.copy(input, new File("S:\\up", "test.pdf").toPath());
        } catch (IOException e) {
            // Error handling
        }
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
