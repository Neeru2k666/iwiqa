package ch.unibe.iwiqa.entity;

import ch.unibe.iwiqa.util.QA_Status;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Marc Jost
 */
@Entity
public class QA implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * Title of the Thesis
     */
    private String title;
    
    /**
     * Starting date of the processing period
     */
    private Date startDate;
    
    /**
     * Ending date of the processing period. Should be automatically set to 
     * startDate + 10 weeks
     */
    private Date endDate;
    
    /**
     * Date when the thesis was effectively handed in e.g. uploaded
     */
    private Date handInDate;
    
    /**
     * Tells the prof if the thesis can be completed. Must have 
     * visited both Fokos
     */
    private boolean canBeCompleted;

    /**
     * Final grade of the thesis
     */
    private double grade;
    
    /**
     * Date when the work was graded
     */
    private Date gradedDate;
    
    /**
     * Identifies the type this QA is, either MA or BA
     */
    private String qaType;
    
    @ManyToOne
    private Advisor advisor;
    
    /**
     * Identifies the status of this QA
     */
    private QA_Status status;
    
    @OneToMany(mappedBy = "qa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FoKoRegistration> participatingIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getHandInDate() {
        return handInDate;
    }

    public void setHandInDate(Date handInDate) {
        this.handInDate = handInDate;
    }

    public boolean isCanBeCompleted() {
        return canBeCompleted;
    }

    public void setCanBeCompleted(boolean canBeCompleted) {
        this.canBeCompleted = canBeCompleted;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getGradedDate() {
        return gradedDate;
    }

    public void setGradedDate(Date gradedDate) {
        this.gradedDate = gradedDate;
    }

    public String getQaType() {
        return qaType;
    }

    public void setQaType(String qaType) {
        this.qaType = qaType;
    }

    public QA_Status getStatus() {
        return status;
    }

    public void setStatus(QA_Status status) {
        this.status = status;
    }

    public List<FoKoRegistration> getParticipatingIn() {
        return participatingIn;
    }

    public void setParticipatingIn(List<FoKoRegistration> participatingIn) {
        this.participatingIn = participatingIn;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
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
        if (!(object instanceof QA)) {
            return false;
        }
        QA other = (QA) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.unibe.iwiqa.entity.QA[ id=" + id + " ]";
    }
    
}
