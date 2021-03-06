package ch.unibe.iwiqa.entity;

import ch.unibe.iwiqa.util.QA_Status;
import ch.unibe.iwiqa.util.QA_Type;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marc Jost
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "QA.findAllByStudent", query = "SELECT f FROM QA f WHERE f.student = :student"),
    @NamedQuery(name = "QA.findOpenAndNotAbortedQAsByAdvisor", query = "SELECT f FROM QA f WHERE f.advisor = :advisor AND ((f.status <> ch.unibe.iwiqa.util.QA_Status.QA_COMPLETED) AND (f.status <> ch.unibe.iwiqa.util.QA_Status.QA_ABORTED)) ORDER BY f.endingDate ASC"),
    @NamedQuery(name = "QA.findAllOpenAndNotAbortedQAs", query = "SELECT f FROM QA f WHERE ((f.status <> ch.unibe.iwiqa.util.QA_Status.QA_COMPLETED) AND (f.status <> ch.unibe.iwiqa.util.QA_Status.QA_ABORTED))"),
    @NamedQuery(name = "QA.findOpenUnremindedOfEndingInOneWeekQAs", query = "SELECT f FROM QA f WHERE ((f.status = ch.unibe.iwiqa.util.QA_Status.PROPOSAL_IN_PROGRESS) OR (f.status = ch.unibe.iwiqa.util.QA_Status.PROPOSAL_ACCEPTED)) AND f.receivedReminderEndingOneWeek = FALSE"),
    @NamedQuery(name = "QA.findHandedInQAs", query = "SELECT f FROM QA f WHERE f.status = ch.unibe.iwiqa.util.QA_Status.QA_HANDED_IN"),
    @NamedQuery(name = "QA.findGradedQAs", query = "SELECT f FROM QA f WHERE f.status = ch.unibe.iwiqa.util.QA_Status.QA_GRADED")
})
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
    @Temporal(TemporalType.DATE)
    private Date startingDate;
    
    /**
     * Ending date of the processing period. Should be automatically set to 
 startingDate + 10 weeks
     */
    @Temporal(TemporalType.DATE)
    private Date endingDate;
    
    /**
     * Date when the thesis was effectively handed in e.g. uploaded
     */
    @Temporal(TemporalType.DATE)
    private Date handInDate;
    
    /**
     * Tells the prof if the thesis can be completed. Must have 
     * visited both Fokos
     */
    private boolean canBeCompleted = false;

    /**
     * Final grade of the thesis. Default value of new QAs is zero
     */
    private double grade = 0;
    
    private String gradeAnnouncementLocalURI = "";
    
    /**
     * Date when the work was graded
     */
    @Temporal(TemporalType.DATE)
    private Date gradedDate;
    
    /**
     * Identifies the type this QA is, either MA or BA
     */
    private QA_Type qaType;
    
    @ManyToOne
    @JoinColumn
    private Advisor advisor;
    
    @ManyToOne
    @JoinColumn
    private Student student;
    
    /**
     * Identifies the status of this QA. Default value of new QAs is PROPOSAL_IN_PROGRESS
     */
    private QA_Status status = QA_Status.PROPOSAL_IN_PROGRESS;
    
    @OneToMany(mappedBy = "qa", fetch = FetchType.EAGER)
    private List<FoKoRegistration> participatingIn = new ArrayList<>();
    
    /**
     * True if Student and Advisor were notified about ending date in one week
     */
    private boolean receivedReminderEndingOneWeek = false;

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

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
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

    public QA_Type getQaType() {
        return qaType;
    }

    public void setQaType(QA_Type qaType) {
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
    
    public void addFoKoRegistration(FoKoRegistration reg) {
        this.participatingIn.add(reg);
    }
    
    public void removeFoKoRegistration(FoKoRegistration reg) {
        this.participatingIn.remove(reg);
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getGradeAnnouncementLocalURI() {
        return gradeAnnouncementLocalURI;
    }

    public void setGradeAnnouncementLocalURI(String gradeAnnouncementLocalURI) {
        this.gradeAnnouncementLocalURI = gradeAnnouncementLocalURI;
    }

    public boolean isReceivedReminderEndingOneWeek() {
        return receivedReminderEndingOneWeek;
    }

    public void setReceivedReminderEndingOneWeek(boolean receivedReminderEndingOneWeek) {
        this.receivedReminderEndingOneWeek = receivedReminderEndingOneWeek;
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
