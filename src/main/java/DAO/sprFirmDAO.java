/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.sprFirm;
import interfaces.beanDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author vasil
 */
public class sprFirmDAO implements beanDAOInterface<sprFirm, Long> {

    private Logger log = Logger.getLogger(sprFirmDAO.class);
    private DataSource dataSource;
    private JdbcTemplate jdЬcTemplate;

    /* public sprFirmDAO() {
        this.dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource("jdbc:postgresql://192.168.1.250:5432/service_desk", "postgres", "123");
        this.jdЬcTemplate = new JdbcTemplate(dataSource);
    }*/
    public sprFirmDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdЬcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public sprFirm getItemById(Long id) {
        sprFirm res = null;
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception e) {
            log.error(e);
        }
        return res;
    }

    @Override
    public List<sprFirm> getItemList() {
        try {
            return (List<sprFirm>) jdЬcTemplate.query("SELECT t.id, t.f_name FROM t_spr_firm t",
                    (ResultSet rs, int rowNum) -> new sprFirm(rs.getLong("id"), rs.getString("f_name")));
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    @Override
    public List<sprFirm> getItemList(Long startIndex, Long itemCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long addItem(sprFirm Item) {
        try {
            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            this.jdЬcTemplate.update((Connection conn) -> {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO public.t_spr_firm(f_name) VALUES (?);", new String[]{"id"});
                ps.setString(1, Item.getName());
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
    public boolean deleteItem(sprFirm Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateItem(sprFirm Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
