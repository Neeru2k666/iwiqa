package ch.unibe.iwiqa.entity;

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
    @NamedQuery(name = "FoKo.findAllOrderByDate", query = "SELECT f FROM FoKo f ORDER BY f.startingDate ASC"),
    @NamedQuery(name = "FoKo.findAllInFutureOrderByDate", query = "SELECT f FROM FoKo f WHERE f.startingDate >= CURRENT_TIMESTAMP ORDER BY f.startingDate"),
    @NamedQuery(name = "FoKo.findUnremindedInFutureFoKos", query = "SELECT f FROM FoKo f WHERE f.startingDate >= CURRENT_TIMESTAMP AND f.sentOutReminderOneWeek = FALSE")
})
public class FoKo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startingDate;
    
    private String room;
    
    @OneToMany(mappedBy = "foko", fetch = FetchType.EAGER)
    private List<FoKoRegistration> participants = new ArrayList<>();
    
    private boolean sentOutReminderOneWeek = false;
    
    @ManyToOne
    @JoinColumn
    private Advisor responsibleAdvisor;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<FoKoRegistration> getParticipants() {
        return participants;
    }

    public void setParticipants(List<FoKoRegistration> participants) {
        this.participants = participants;
    }
    
    public void addParticipant(FoKoRegistration reg) {
        this.participants.add(reg);
    }
    
    public void removeParticipant(FoKoRegistration reg) {
        this.participants.remove(reg);
    }

    public boolean isSentOutReminderOneWeek() {
        return sentOutReminderOneWeek;
    }

    public void setSentOutReminderOneWeek(boolean sentOutReminderOneWeek) {
        this.sentOutReminderOneWeek = sentOutReminderOneWeek;
    }

    public Advisor getResponsibleAdvisor() {
        return responsibleAdvisor;
    }

    public void setResponsibleAdvisor(Advisor responsibleAdvisor) {
        this.responsibleAdvisor = responsibleAdvisor;
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
        if (!(object instanceof FoKo)) {
            return false;
        }
        FoKo other = (FoKo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.unibe.iwiqa.entity.FoKo[ id=" + id + " ]";
    }

    
    
}
