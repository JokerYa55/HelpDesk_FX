/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;



/**
 *
 * @author vasil
 */
public class tIncident {

    private Long id;
    private Date FDate;
    private Long FFirmId;
    private String FFirmName;
    private Long FServiceId;
    private String FServiceName;
    private String FComment;
    private Date FDateCreated;
    private Long FUserId;
    private String FUserName;
    private Long FIncidentStatusId;
    private String FIncidentStatusName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFDate() {
        return FDate;
    }

    public void setFDate(Date FDate) {
        this.FDate = FDate;
    }

    public Long getFFirmId() {
        return FFirmId;
    }

    public void setFFirmId(Long FFirmId) {
        this.FFirmId = FFirmId;
    }

    public Long getFServiceId() {
        return FServiceId;
    }

    public void setFServiceId(Long FServiceId) {
        this.FServiceId = FServiceId;
    }

    public String getFComment() {
        return FComment;
    }

    public void setFComment(String FComment) {
        this.FComment = FComment;
    }

    public Date getFDateCreated() {
        return FDateCreated;
    }

    public void setFDateCreated(Date FDateCreated) {
        this.FDateCreated = FDateCreated;
    }

    public Long getFUserId() {
        return FUserId;
    }

    public void setFUserId(Long FUserId) {
        this.FUserId = FUserId;
    }

    public Long getFIncidentStatusId() {
        return FIncidentStatusId;
    }

    public void setFIncidentStatusId(Long FIncidentStatusId) {
        this.FIncidentStatusId = FIncidentStatusId;
    }

    public String getFFirmName() {
        return FFirmName;
    }

    public void setFFirmName(String FFirmName) {
        this.FFirmName = FFirmName;
    }

    public String getFServiceName() {
        return FServiceName;
    }

    public void setFServiceName(String FServiceName) {
        this.FServiceName = FServiceName;
    }

    public String getFUserName() {
        return FUserName;
    }

    public void setFUserNAme(String FUserName) {
        this.FUserName = FUserName;
    }

    public String getFIncidentStatusName() {
        return FIncidentStatusName;
    }

    public void setFIncidentStatusName(String FIncidentStatusName) {
        this.FIncidentStatusName = FIncidentStatusName;
    }

    public tIncident(Long id, Date FDate, Long FFirmId, String FFirmName, Long FServiceId, String FServiceName, String FComment, Date FDateCreated, Long FUserId, String FUserName, Long FIncidentStatusId, String FIncidentStatusName) {
        this.id = id;
        this.FDate = FDate;
        this.FFirmId = FFirmId;
        this.FFirmName = FFirmName;
        this.FServiceId = FServiceId;
        this.FServiceName = FServiceName;
        this.FComment = FComment;
        this.FDateCreated = FDateCreated;
        this.FUserId = FUserId;
        this.FUserName = FUserName;
        this.FIncidentStatusId = FIncidentStatusId;
        this.FIncidentStatusName = FIncidentStatusName;
    }

    public tIncident(Long id, Date FDate, Long FFirmId, Long FServiceId, String FComment, Date FDateCreated, Long FUserId, Long FIncidentStatusId) {
        this.id = id;
        this.FDate = FDate;
        this.FFirmId = FFirmId;
        this.FServiceId = FServiceId;
        this.FComment = FComment;
        this.FDateCreated = FDateCreated;
        this.FUserId = FUserId;
        this.FIncidentStatusId = FIncidentStatusId;
    }

    public tIncident() {
    }

    @Override
    public String toString() {
        return "tIncident{" + "id=" + id + ", FDate=" + FDate + ", FFirmId=" + FFirmId + ", FFirmName=" + FFirmName + ", FServiceId=" + FServiceId + ", FServiceName=" + FServiceName + ", FComment=" + FComment + ", FDateCreated=" + FDateCreated + ", FUserId=" + FUserId + ", FUserNAme=" + FUserName + ", FIncidentStatusId=" + FIncidentStatusId + ", FIncidentStatusName=" + FIncidentStatusName + '}';
    }

}
