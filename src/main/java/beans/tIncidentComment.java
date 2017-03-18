/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author vasil
 */
public class tIncidentComment {

    /*
    id_incident bigint NOT NULL, -- Код инцидента
    id bigint NOT NULL DEFAULT nextval('t_incident_comment_id_seq'::regclass), -- Уникальный код
    f_parent_id bigint, -- Ссылка на родительский комментарий
    f_comment_type_id bigint NOT NULL, -- Код типа комментария
    f_date_created timestamp without time zone NOT NULL DEFAULT now(), -- Дата создания комментария
    f_user_id bigint NOT NULL, -- Код пользователя создавшего комментарий
    f_comment text, -- Текст комментария
    
     */
    private Logger log = Logger.getLogger(tIncidentComment.class);
    private Long Id;
    private Long IdIncident;
    private Long ParentId;
    private Long CommentType;
    private String CommentTypeName;
    private Date DateCreated;
    private Long UserId;
    private String UserName;
    private String Comment;
    private Long Level;

    public tIncidentComment(Long Id, Long IdIncident, Long ParentId, Long CommentType, String CommentTypeName, Date DateCreated, Long UserId, String UserName, String Comment, Long Level) {
        this.Id = Id;
        this.IdIncident = IdIncident;
        this.ParentId = ParentId;
        this.CommentType = CommentType;
        this.CommentTypeName = CommentTypeName;
        this.DateCreated = DateCreated;
        this.UserId = UserId;
        this.UserName = UserName;
        this.Comment = Comment;
        this.Level = Level;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getIdIncident() {
        return IdIncident;
    }

    public void setIdIncident(Long IdIncident) {
        this.IdIncident = IdIncident;
    }

    public Long getParentId() {
        return ParentId;
    }

    public void setParentId(Long ParentId) {
        this.ParentId = ParentId;
    }

    public Long getCommentType() {
        return CommentType;
    }

    public void setCommentType(Long CommentType) {
        this.CommentType = CommentType;
    }

    public Date getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(Date DateCreated) {
        this.DateCreated = DateCreated;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long UserId) {
        this.UserId = UserId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getCommentTypeName() {
        return CommentTypeName;
    }

    public void setCommentTypeName(String CommentTypeName) {
        this.CommentTypeName = CommentTypeName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public Long getLevel() {
        return Level;
    }

}
