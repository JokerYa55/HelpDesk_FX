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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vasil
 */
@Entity
@Table(name = "t_firm_users")
@NamedQueries({
    @NamedQuery(name = "TFirmUsers.findAll", query = "SELECT t FROM TFirmUsers t")
    , @NamedQuery(name = "TFirmUsers.findById", query = "SELECT t FROM TFirmUsers t WHERE t.id = :id")})
public class TFirmUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "f_firm_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TSprFirm fFirmId;
    @JoinColumn(name = "f_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TSprUsers fUserId;

    public TFirmUsers() {
    }

    public TFirmUsers(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TSprFirm getFFirmId() {
        return fFirmId;
    }

    public void setFFirmId(TSprFirm fFirmId) {
        this.fFirmId = fFirmId;
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
        if (!(object instanceof TFirmUsers)) {
            return false;
        }
        TFirmUsers other = (TFirmUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans_JPA.TFirmUsers[ id=" + id + " ]";
    }
    
}
