/*
 */
package ch.unibe.iwiqa.web.rest;

import ch.unibe.iwiqa.entity.QA;
import ch.unibe.iwiqa.entity.dao.QAFacade;
import java.io.File;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author Marc Jost
 */
@Path("/files/{qaid}")
public class FileResource {
    
    @Inject
    private QAFacade qAFacade;
    
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("qaid") String id){
        QA qa = qAFacade.find(Long.parseLong(id));
        String filePath = qa.getGradeAnnouncementLocalURI();
        File file = new File(filePath);
        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment; filename=Notenmeldung.docx");
        return response.build();
    }
}
