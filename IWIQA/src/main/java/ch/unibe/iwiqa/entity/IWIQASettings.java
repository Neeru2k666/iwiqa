/*
 */
package ch.unibe.iwiqa.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Marc Jost
 */
@Entity
public class IWIQASettings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String gradeAnnouncerEmail;
    
    private String pathQAFileUploadFolder;
    
    private String pathGradeAnnouncementFolder;
    
    private String pathGradeAnnouncementTemplate;

    public String getGradeAnnouncerEmail() {
        return gradeAnnouncerEmail;
    }

    public void setGradeAnnouncerEmail(String gradeAnnouncerEmail) {
        this.gradeAnnouncerEmail = gradeAnnouncerEmail;
    }

    public String getPathQAFileUploadFolder() {
        return pathQAFileUploadFolder;
    }

    public void setPathQAFileUploadFolder(String pathQAFileUploadFolder) {
        this.pathQAFileUploadFolder = pathQAFileUploadFolder;
    }

    public String getPathGradeAnnouncementFolder() {
        return pathGradeAnnouncementFolder;
    }

    public void setPathGradeAnnouncementFolder(String pathGradeAnnouncementFolder) {
        this.pathGradeAnnouncementFolder = pathGradeAnnouncementFolder;
    }

    public String getPathGradeAnnouncementTemplate() {
        return pathGradeAnnouncementTemplate;
    }

    public void setPathGradeAnnouncementTemplate(String pathGradeAnnouncementTemplate) {
        this.pathGradeAnnouncementTemplate = pathGradeAnnouncementTemplate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IWIQASettings)) {
            return false;
        }
        IWIQASettings other = (IWIQASettings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.unibe.iwiqa.entity.IWIQASettings[ id=" + id + " ]";
    }
    
}
