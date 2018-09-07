/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_JPA;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vasil
 */
@Entity
@Table(name = "t_spr_firm")
@NamedQueries({
    @NamedQuery(name = "TSprFirm.findAll", query = "SELECT t FROM TSprFirm t")
    , @NamedQuery(name = "TSprFirm.findById", query = "SELECT t FROM TSprFirm t WHERE t.id = :id")
    , @NamedQuery(name = "TSprFirm.findByFName", query = "SELECT t FROM TSprFirm t WHERE t.fName = :fName")})
public class TSprFirm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "f_name")
    private String fName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fFirmId")
    private Collection<TFirmUsers> tFirmUsersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fFirmId")
    private Collection<TIncident> tIncidentCollection;

    public TSprFirm() {
    }

    public TSprFirm(Long id) {
        this.id = id;
    }

    public TSprFirm(Long id, String fName) {
        this.id = id;
        this.fName = fName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public Collection<TFirmUsers> getTFirmUsersCollection() {
        return tFirmUsersCollection;
    }

    public void setTFirmUsersCollection(Collection<TFirmUsers> tFirmUsersCollection) {
        this.tFirmUsersCollection = tFirmUsersCollection;
    }

    public Collection<TIncident> getTIncidentCollection() {
        return tIncidentCollection;
    }

    public void setTIncidentCollection(Collection<TIncident> tIncidentCollection) {
        this.tIncidentCollection = tIncidentCollection;
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
        if (!(object instanceof TSprFirm)) {
            return false;
        }
        TSprFirm other = (TSprFirm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans_JPA.TSprFirm[ id=" + id + " ]";
    }
    
}
