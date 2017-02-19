/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.sprIncidentStatus;
import interfaces.beanDAOInterface;
import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author vasil
 */
public class sprIncidentStatusDAO implements beanDAOInterface<sprIncidentStatus, Long> {

    private Logger log = Logger.getLogger(sprIncidentStatusDAO.class);
    private DataSource dataSource;
    private JdbcTemplate jdЬcTemplate;

    public sprIncidentStatusDAO() {
        this.dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://192.168.1.250:5432/service_desk", "postgres", "123");
        this.jdЬcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public sprIncidentStatus getItemById(Long id) {
        try {
            return (sprIncidentStatus) jdЬcTemplate.queryForObject("SELECT t.id, t.f_name FROM t_spr_incident_status t WHERE t.id = ?", new Object[]{id}, sprIncidentStatus.class);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<sprIncidentStatus> getItemList() {
        try {
            return (List<sprIncidentStatus>) jdЬcTemplate.query("SELECT t.id, t.f_name FROM t_spr_incident_status t",
                    (ResultSet rs, int rowNum) -> new sprIncidentStatus(rs.getLong("id"), rs.getString("f_name")));
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<sprIncidentStatus> getItemList(Long startIndex, Long itemCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long addItem(sprIncidentStatus Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteItem(sprIncidentStatus Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateItem(sprIncidentStatus Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
