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
import javax.persistence.CascadeType;
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
@Table(name = "t_incident")
@NamedQueries({
    @NamedQuery(name = "TIncident.findAll", query = "SELECT t FROM TIncident t")
    , @NamedQuery(name = "TIncident.findById", query = "SELECT t FROM TIncident t WHERE t.id = :id")
    , @NamedQuery(name = "TIncident.findByFDate", query = "SELECT t FROM TIncident t WHERE t.fDate = :fDate")
    , @NamedQuery(name = "TIncident.findByFComment", query = "SELECT t FROM TIncident t WHERE t.fComment = :fComment")
    , @NamedQuery(name = "TIncident.findByFDateCreated", query = "SELECT t FROM TIncident t WHERE t.fDateCreated = :fDateCreated")})
public class TIncident implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "f_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDate;
    @Basic(optional = false)
    @Column(name = "f_comment")
    private String fComment;
    @Basic(optional = false)
    @Column(name = "f_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fDateCreated;
    @JoinColumn(name = "f_firm_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TSprFirm fFirmId;
    @JoinColumn(name = "f_incident_status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TSprIncidentStatus fIncidentStatusId;
    @JoinColumn(name = "f_service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TSprService fServiceId;
    @JoinColumn(name = "f_user_id", referencedColumnName = "id")
    @ManyToOne
    private TSprUsers fUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIncident")
    private Collection<TIncidentComment> tIncidentCommentCollection;

    public TIncident() {
    }

    public TIncident(Long id) {
        this.id = id;
    }

    public TIncident(Long id, Date fDate, String fComment, Date fDateCreated) {
        this.id = id;
        this.fDate = fDate;
        this.fComment = fComment;
        this.fDateCreated = fDateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFDate() {
        return fDate;
    }

    public void setFDate(Date fDate) {
        this.fDate = fDate;
    }

    public String getFComment() {
        return fComment;
    }

    public void setFComment(String fComment) {
        this.fComment = fComment;
    }

    public Date getFDateCreated() {
        return fDateCreated;
    }

    public void setFDateCreated(Date fDateCreated) {
        this.fDateCreated = fDateCreated;
    }

    public TSprFirm getFFirmId() {
        return fFirmId;
    }

    public void setFFirmId(TSprFirm fFirmId) {
        this.fFirmId = fFirmId;
    }

    public TSprIncidentStatus getFIncidentStatusId() {
        return fIncidentStatusId;
    }

    public void setFIncidentStatusId(TSprIncidentStatus fIncidentStatusId) {
        this.fIncidentStatusId = fIncidentStatusId;
    }

    public TSprService getFServiceId() {
        return fServiceId;
    }

    public void setFServiceId(TSprService fServiceId) {
        this.fServiceId = fServiceId;
    }

    public TSprUsers getFUserId() {
        return fUserId;
    }

    public void setFUserId(TSprUsers fUserId) {
        this.fUserId = fUserId;
    }

    public Collection<TIncidentComment> getTIncidentCommentCollection() {
        return tIncidentCommentCollection;
    }

    public void setTIncidentCommentCollection(Collection<TIncidentComment> tIncidentCommentCollection) {
        this.tIncidentCommentCollection = tIncidentCommentCollection;
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
        if (!(object instanceof TIncident)) {
            return false;
        }
        TIncident other = (TIncident) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans_JPA.TIncident[ id=" + id + " ]";
    }
    
}
