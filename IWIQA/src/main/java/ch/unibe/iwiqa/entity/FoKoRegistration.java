package ch.unibe.iwiqa.entity;

import ch.unibe.iwiqa.util.FoKo_ParticipateAs;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Marc Jost
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "FoKoRegistration.findByQA", query = "SELECT f FROM FoKoRegistration f WHERE f.qa = :qa")
})
public class FoKoRegistration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn
    private QA qa;
    
    @ManyToOne
    @JoinColumn
    private FoKo foko;
    
    @ManyToOne
    @JoinColumn
    private Student student;
    
    private FoKo_ParticipateAs participatingAs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QA getQa() {
        return qa;
    }

    public void setQa(QA qa) {
        this.qa = qa;
    }

    public FoKo getFoko() {
        return foko;
    }

    public void setFoko(FoKo foko) {
        this.foko = foko;
    }

    public FoKo_ParticipateAs getParticipatingAs() {
        return participatingAs;
    }

    public void setParticipatingAs(FoKo_ParticipateAs participatingAs) {
        this.participatingAs = participatingAs;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        if (!(object instanceof FoKoRegistration)) {
            return false;
        }
        FoKoRegistration other = (FoKoRegistration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.unibe.iwiqa.entity.FoKoParticipant[ id=" + id + " ]";
    }
    
}
