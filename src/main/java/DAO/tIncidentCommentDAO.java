/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.tIncidentComment;
import interfaces.beanDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.TreeSet;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author vasil
 */
public class tIncidentCommentDAO implements beanDAOInterface<tIncidentComment, Long> {

    private Logger log = Logger.getLogger(sprFirmDAO.class);
    private DataSource dataSource;
    private JdbcTemplate jdЬcTemplate;

    public tIncidentCommentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdЬcTemplate = new JdbcTemplate(dataSource);
    }

    /*
    WITH RECURSIVE r AS (
	SELECT 1 as f_level,
	t.id, 
	t.f_parent_id,
	t.f_date_created, 
	t.f_comment
FROM 
  public.t_incident_comment t
WHERE 
  t.f_parent_id is null
  AND t.id_incident = 1

  UNION ALL

  SELECT 
	r.f_level + 1 as f_level,
	t1.id, 
	t1.f_parent_id,
	t1.f_date_created, 
	t1.f_comment
FROM 
  public.t_incident_comment t1, r
WHERE 
  t1.f_parent_id = r.id
)

SELECT * FROM r
     */
    @Override
    public tIncidentComment getItemById(Long id) {
        tIncidentComment res = null;
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception e) {
            log.error(e);
        }
        return res;
    }

    @Override
    public List<tIncidentComment> getItemList() {
        try {
            return (List<tIncidentComment>) jdЬcTemplate.query("SELECT \n"
                    + "  t.id, \n"
                    + "  t.id_incident, \n"
                    + "  t.f_parent_id, \n"
                    + "  t.f_comment_type_id, \n"
                    + "  t_spr_comment_type.f_name AS f_comment_type_name, \n"
                    + "  t.f_date_created, \n"
                    + "  t.f_user_id, \n"
                    + "  t1.f_name AS f_user_name, \n"
                    + "  t.f_comment\n"
                    + "FROM \n"
                    + "  public.t_incident_comment t, \n"
                    + "  public.t_spr_users t1, \n"
                    + "  public.t_spr_comment_type\n"
                    + "WHERE \n"
                    + "  t.f_user_id = t1.id AND\n"
                    + "  t.f_comment_type_id = t_spr_comment_type.id\n"
                    + "ORDER BY\n"
                    + "  t.f_date_created ASC",
                    (ResultSet rs, int rowNum) -> new tIncidentComment(rs.getLong("id"),
                            rs.getLong("id_incident"),
                            rs.getLong("f_parent_id"),
                            rs.getLong("f_comment_type_id"),
                            rs.getString("f_comment_type_name"),
                            rs.getDate("f_date_created"),
                            rs.getLong("f_user_id"),
                            rs.getString("f_user_name"),
                            rs.getString("f_comment"),
                            new Long(1)));
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public List<tIncidentComment> getItemList(Long incId) {
        try {
            log.debug("getItemList incId = " + incId);
            return (List<tIncidentComment>) jdЬcTemplate.query("WITH RECURSIVE r AS (\n"
                    + "	SELECT 1 as f_level,\n"
                    + "	t.id, \n"
                    + "	t.id_incident,\n"
                    + "	t.f_parent_id,\n"
                    + "	t.f_comment_type_id,\n"
                    + "	t5.f_name AS f_comment_type_name,\n"
                    + "	t.f_date_created, \n"
                    + "	t.f_user_id,\n"
                    + "	t7.f_name AS f_user_name,\n"
                    + "	t.f_comment\n"
                    + "FROM \n"
                    + "  t_incident_comment t, \n"
                    + "  t_spr_comment_type t5,\n"
                    + "  t_spr_users t7\n"
                    + "WHERE \n"
                    + "  t.f_parent_id is null\n"
                    + "  AND t5.id = t.f_comment_type_id\n"
                    + "  AND t7.id = t.f_user_id\n"
                    + "  AND t.id_incident = 18\n"
                    + "\n"
                    + "  UNION ALL\n"
                    + "\n"
                    + "  SELECT \n"
                    + "	r.f_level + 1 as f_level,\n"
                    + "	t1.id,\n"
                    + "	t1.id_incident,\n"
                    + "	t1.f_parent_id,\n"
                    + "	t1.f_comment_type_id,\n"
                    + "	t6.f_name AS f_comment_type_name,\n"
                    + "	t1.f_date_created, \n"
                    + "	t1.f_user_id,\n"
                    + "	t8.f_name AS f_user_name,\n"
                    + "	t1.f_comment\n"
                    + "FROM \n"
                    + "  t_incident_comment t1, r,\n"
                    + "  t_spr_comment_type t6,\n"
                    + "  t_spr_users t8\n"
                    + "WHERE \n"
                    + "  t1.f_parent_id = r.id\n"
                    + "  AND t6.id = t1.f_comment_type_id  \n"
                    + "  AND t8.id = t1.f_user_id\n"
                    + ")\n"
                    + "\n"
                    + "SELECT * FROM r t3\n"
                    + "ORDER BY t3.id, t3.f_date_created ",
                    (ResultSet rs, int rowNum) -> new tIncidentComment(
                            rs.getLong("id"),
                            rs.getLong("id_incident"),
                            rs.getLong("f_parent_id"),
                            rs.getLong("f_comment_type_id"),
                            rs.getString("f_comment_type_name"),
                            rs.getDate("f_date_created"),
                            rs.getLong("f_user_id"),
                            rs.getString("f_user_name"),
                            rs.getString("f_comment"),
                            rs.getLong("f_level")
                    ));
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<tIncidentComment> getItemList(Long startIndex, Long itemCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long addItem(tIncidentComment Item) {
        try {
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            this.jdЬcTemplate.update((Connection conn) -> {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO t_incident_comment("
                                                                + " id_incident, "                                                                
                                                                //+ " f_parent_id, "
                                                                + " f_comment_type_id, "
                                                                //+ " f_date_created, "
                                                                + " f_user_id,"
                                                                + " f_comment)"
                                                                + " VALUES (?, ?, ?, ?);", new String[]{"id"});
                ps.setLong(1, Item.getIdIncident());
                ps.setLong(2, Item.getCommentType());
                ps.setLong(3, Item.getUserId());
                ps.setString(4, Item.getComment());
                return ps;
            }, generatedKeyHolder);
            log.info(generatedKeyHolder.getKey().longValue());
            return generatedKeyHolder.getKey() == null ? null : generatedKeyHolder.getKey().intValue();
        } catch (Exception e) {
            log.error(e);
            return -1;
        }
    }

    @Override
    public boolean deleteItem(tIncidentComment Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateItem(tIncidentComment Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
