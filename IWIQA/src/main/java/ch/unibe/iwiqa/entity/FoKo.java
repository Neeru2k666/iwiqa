package ch.unibe.iwiqa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marc Jost
 */
@Entity
public class FoKo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Calendar startingDate;
    
    @Temporal(TemporalType.DATE)
    private Calendar endingDate;
    
    private String room;
    
    @OneToMany(mappedBy = "foko", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FoKoRegistration> participants = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Calendar startingDate) {
        this.startingDate = startingDate;
    }

    public Calendar getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Calendar endingDate) {
        this.endingDate = endingDate;
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
