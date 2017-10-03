/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_JPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vasil
 */
@Entity
@Table(name = "t_firm_service")
@NamedQueries({
    @NamedQuery(name = "TFirmService.findAll", query = "SELECT t FROM TFirmService t")
    , @NamedQuery(name = "TFirmService.findById", query = "SELECT t FROM TFirmService t WHERE t.id = :id")
    , @NamedQuery(name = "TFirmService.findByFFirmId", query = "SELECT t FROM TFirmService t WHERE t.fFirmId = :fFirmId")
    , @NamedQuery(name = "TFirmService.findByFServiceId", query = "SELECT t FROM TFirmService t WHERE t.fServiceId = :fServiceId")})
public class TFirmService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "f_firm_id")
    private long fFirmId;
    @Basic(optional = false)
    @Column(name = "f_service_id")
    private long fServiceId;

    public TFirmService() {
    }

    public TFirmService(Long id) {
        this.id = id;
    }

    public TFirmService(Long id, long fFirmId, long fServiceId) {
        this.id = id;
        this.fFirmId = fFirmId;
        this.fServiceId = fServiceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFFirmId() {
        return fFirmId;
    }

    public void setFFirmId(long fFirmId) {
        this.fFirmId = fFirmId;
    }

    public long getFServiceId() {
        return fServiceId;
    }

    public void setFServiceId(long fServiceId) {
        this.fServiceId = fServiceId;
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
        if (!(object instanceof TFirmService)) {
            return false;
        }
        TFirmService other = (TFirmService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans_JPA.TFirmService[ id=" + id + " ]";
    }
    
}
