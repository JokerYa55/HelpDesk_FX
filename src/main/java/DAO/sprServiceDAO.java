/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.sprService;
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
public class sprServiceDAO implements beanDAOInterface<sprService, Long> {

    private Logger log = Logger.getLogger(sprServiceDAO.class);
    private DataSource dataSource;
    private JdbcTemplate jdЬcTemplate;

    public sprServiceDAO() {
        this.dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://localhost:5432/service_desk", "postgres", "123");
        this.jdЬcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public sprService getItemById(Long id) {
        sprService res = null;
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception e) {
            log.error(e);
        }
        return res;
    }

    @Override
    public List<sprService> getItemList() {
        try {
            return (List<sprService>) jdЬcTemplate.query("SELECT t.id, t.f_name FROM t_spr_service t",
                    (ResultSet rs, int rowNum) -> new sprService(rs.getLong("id"), rs.getString("f_name")));
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<sprService> getItemList(Long startIndex, Long itemCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long addItem(sprService Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteItem(sprService Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateItem(sprService Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
