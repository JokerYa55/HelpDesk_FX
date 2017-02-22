/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.tIncident;
import interfaces.beanDAOInterface;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author vasil
 */
public class tIncidentDAO implements beanDAOInterface<tIncident, Long> {

    private Logger log = Logger.getLogger(tIncidentDAO.class);
    private DataSource dataSource;
    private JdbcTemplate jdЬcTemplate;

    /*public tIncidentDAO() {
        this.dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://192.168.1.250:5432/service_desk", "postgres", "123");
        this.jdЬcTemplate = new JdbcTemplate(dataSource);
    }*/

    public tIncidentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdЬcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public tIncident getItemById(Long id) {
        try {
            return (tIncident) jdЬcTemplate.queryForObject("SELECT t.id, t.f_name FROM t_spr_incident_status t WHERE t.id = ?", new Object[]{id}, tIncident.class);
        } catch (DataAccessException e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<tIncident> getItemList() {
        try {
            log.debug("getItemList");
            return (List<tIncident>) jdЬcTemplate.query("SELECT \n"
                    + "  t.id, \n"
                    + "  t.f_date, \n"
                    + "  t.f_firm_id, \n"
                    + "  t1.f_name AS f_firm_name, \n"
                    + "  t.f_service_id, \n"
                    + "  t_spr_service.f_name AS f_service_name, \n"
                    + "  t.f_user_id, \n"
                    + "  t_spr_users.f_name AS f_user_name, \n"
                    + "  t.f_incident_status_id, \n"
                    + "  t_spr_incident_status.f_name AS f_incident_status_name, \n"
                    + "  t.f_comment, \n"
                    + "  t.f_date_created\n"
                    + "FROM \n"
                    + "  public.t_incident t, \n"
                    + "  public.t_spr_users, \n"
                    + "  public.t_spr_service, \n"
                    + "  public.t_spr_firm t1, \n"
                    + "  public.t_spr_incident_status\n"
                    + "WHERE \n"
                    + "  t.f_firm_id = t1.id AND\n"
                    + "  t.f_service_id = t_spr_service.id AND\n"
                    + "  t.f_user_id = t_spr_users.id AND\n"
                    + "  t.f_incident_status_id = t_spr_incident_status.id\n"
                    + "ORDER BY\n"
                    + "  t.f_date DESC;",
                    (ResultSet rs, int rowNum) -> new tIncident(rs.getLong("id"),
                            rs.getDate("f_date"),
                            rs.getLong("f_firm_id"),
                            rs.getString("f_firm_name"),
                            rs.getLong("f_service_id"),
                            rs.getString("f_service_name"),
                            rs.getString("f_comment"),
                            rs.getDate("f_date_created"),
                            rs.getLong("f_user_id"),
                            rs.getString("f_user_name"),
                            rs.getLong("f_incident_status_id"),
                            rs.getString("f_incident_status_name")));
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public List<tIncident> getItemListByStatus(Long statusId) {
        try {
            log.debug("getItemListByStatus");
            return (List<tIncident>) jdЬcTemplate.query("SELECT \n"
                    + "  t.id, \n"
                    + "  t.f_date, \n"
                    + "  t.f_firm_id, \n"
                    + "  t1.f_name AS f_firm_name, \n"
                    + "  t.f_service_id, \n"
                    + "  t_spr_service.f_name AS f_service_name, \n"
                    + "  t.f_user_id, \n"
                    + "  t_spr_users.f_name AS f_user_name, \n"
                    + "  t.f_incident_status_id, \n"
                    + "  t_spr_incident_status.f_name AS f_incident_status_name, \n"
                    + "  t.f_comment, \n"
                    + "  t.f_date_created\n"
                    + "FROM \n"
                    + "  public.t_incident t, \n"
                    + "  public.t_spr_users, \n"
                    + "  public.t_spr_service, \n"
                    + "  public.t_spr_firm t1, \n"
                    + "  public.t_spr_incident_status\n"
                    + "WHERE \n"
                    + "  t.f_firm_id = t1.id AND\n"
                    + "  t.f_service_id = t_spr_service.id AND\n"
                    + "  t.f_user_id = t_spr_users.id AND\n"
                    + "  t.f_incident_status_id = t_spr_incident_status.id AND \n"
                    + "  t_spr_incident_status.id = " + statusId
                    + " ORDER BY\n"
                    + "  t.f_date DESC;",
                    (ResultSet rs, int rowNum) -> new tIncident(rs.getLong("id"),
                            rs.getDate("f_date"),
                            rs.getLong("f_firm_id"),
                            rs.getString("f_firm_name"),
                            rs.getLong("f_service_id"),
                            rs.getString("f_service_name"),
                            rs.getString("f_comment"),
                            rs.getDate("f_date_created"),
                            rs.getLong("f_user_id"),
                            rs.getString("f_user_name"),
                            rs.getLong("f_incident_status_id"),
                            rs.getString("f_incident_status_name")));
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<tIncident> getItemList(Long startIndex, Long itemCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long addItem(tIncident Item) {
        log.info("Добавляем инцидент -> " + Item);
        try {
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            this.jdЬcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO t_incident(\n"
                            + "f_date, "
                            + "f_firm_id, "
                            + "f_service_id, "
                            + "f_comment, "
                            + "f_date_created, \n"
                            + "f_user_id, "
                            + "f_incident_status_id)\n"
                            + " VALUES (?, ?, ?, ?, ?,  ?, ?)", new String[]{"id"});
                    ps.setDate(1, new Date(Item.getFDate().getTime()));
                    ps.setLong(2, Item.getFFirmId());
                    ps.setLong(3, Item.getFServiceId());
                    ps.setString(4, Item.getFComment());
                    ps.setDate(5, new Date(Item.getFDateCreated().getTime()));
                    ps.setLong(6, Item.getFUserId());
                    ps.setLong(7, Item.getFIncidentStatusId());
                    return ps;
                }
            }, generatedKeyHolder);
            log.info(generatedKeyHolder.getKey().longValue());
            return generatedKeyHolder.getKey() == null ? null : generatedKeyHolder.getKey().intValue();
        } catch (Exception e) {
            log.error(e);
            return -1;
        }
    }

    @Override
    public boolean deleteItem(tIncident Item) {
        log.info("Удаляем элемент -> " + Item);
        boolean flag = false;
        try {
            int rows = this.jdЬcTemplate.update("DELETE FROM t_incident t WHERE t.id = ?", new Object[]{Item.getId()});
            log.info("Обновлено строк : " + rows);
            flag = true;
        } catch (Exception e) {
            log.error(e);
        }
        return flag;
    }

    @Override
    public boolean updateItem(tIncident Item) {
        log.info("Обновляем элемент -> " + Item);
        boolean flag = false;
        try {
            int rows = this.jdЬcTemplate.update("UPDATE t_incident "
                    + "SET f_date=?, "
                    + "f_firm_id=?, "
                    + "f_service_id=?, "
                    + "f_comment=?, "
                    + "f_date_created=?, "
                    + "f_user_id=?, "
                    + "f_incident_status_id=? "
                    + "WHERE id = ?", new Object[]{Item.getFDate(),
                        Item.getFFirmId(),
                        Item.getFServiceId(),
                        Item.getFComment(),
                        Item.getFDateCreated(),
                        Item.getFUserId(),
                        Item.getFIncidentStatusId(),
                        Item.getId()});
            flag = true;
        } catch (Exception e) {
            log.error(e);
        }
        return flag;
    }

}
