/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_JPA;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vasil
 */
@Entity
@Table(name = "t_incident_comment")
@NamedQueries({
    @NamedQuery(name = "TIncidentComment.findAll", query = "SELECT t FROM TIncidentComment t")
    , @NamedQuery(name = "TIncidentComment.findById", query = "SELECT t FROM TIncidentComment t WHERE t.id = :id")
    , @NamedQuery(name = "TIncidentComment.findByFDateCreated", query = "SELECT t FROM TIncidentComment t WHERE t.fDateCreated = :fDateCreated")
    , @NamedQuery(name = "TIncidentComment.findByIncident", query = "SELECT t FROM TIncidentComment t WHERE t.idIncident = :idIncident")
    , @NamedQuery(name = "TIncidentComment.findByFComment", query = "SELECT t FROM TIncidentComment t WHERE t.fComment = :fComment")})
public class TIncidentComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "f_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDateCreated;
    @Column(name = "f_comment")
    private String fComment;
    @JoinColumn(name = "id_incident", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TIncident idIncident;
    @OneToMany(mappedBy = "fParentId")
    private Collection<TIncidentComment> tIncidentCommentCollection;
    @JoinColumn(name = "f_parent_id", referencedColumnName = "id")
    @ManyToOne
    private TIncidentComment fParentId;
    @JoinColumn(name = "f_comment_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TSprCommentType fCommentTypeId;
    @JoinColumn(name = "f_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TSprUsers fUserId;

    public TIncidentComment() {
    }

    public TIncidentComment(Long id) {
        this.id = id;
    }

    public TIncidentComment(Long id, Date fDateCreated) {
        this.id = id;
        this.fDateCreated = fDateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFDateCreated() {
        return fDateCreated;
    }

    public void setFDateCreated(Date fDateCreated) {
        this.fDateCreated = fDateCreated;
    }

    public String getFComment() {
        return fComment;
    }

    public void setFComment(String fComment) {
        this.fComment = fComment;
    }

    public TIncident getIdIncident() {
        return idIncident;
    }

    public void setIdIncident(TIncident idIncident) {
        this.idIncident = idIncident;
    }

    public Collection<TIncidentComment> getTIncidentCommentCollection() {
        return tIncidentCommentCollection;
    }

    public void setTIncidentCommentCollection(Collection<TIncidentComment> tIncidentCommentCollection) {
        this.tIncidentCommentCollection = tIncidentCommentCollection;
    }

    public TIncidentComment getFParentId() {
        return fParentId;
    }

    public void setFParentId(TIncidentComment fParentId) {
        this.fParentId = fParentId;
    }

    public TSprCommentType getFCommentTypeId() {
        return fCommentTypeId;
    }

    public void setFCommentTypeId(TSprCommentType fCommentTypeId) {
        this.fCommentTypeId = fCommentTypeId;
    }

    public TSprUsers getFUserId() {
        return fUserId;
    }

    public void setFUserId(TSprUsers fUserId) {
        this.fUserId = fUserId;
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
        if (!(object instanceof TIncidentComment)) {
            return false;
        }
        TIncidentComment other = (TIncidentComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans_JPA.TIncidentComment[ id=" + id + " ]";
    }

}
