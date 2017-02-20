/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import beans.sprUser;
import interfaces.beanDAOInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author vasil
 */
public class sprUsersDAO implements beanDAOInterface<sprUser, Long> {

    private final Logger log = Logger.getLogger(sprUsersDAO.class);
    private DataSource dataSource;
    private JdbcTemplate jdЬcTemplate;

    public sprUsersDAO() {
    }

    public sprUsersDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdЬcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public sprUser getItemById(Long id) {
         try {
            return (sprUser) jdЬcTemplate.queryForObject("SELECT t.id, t.f_login, t.f_pass, t.f_name FROM t_spr_users t WHERE t.id = ?", new Object[]{id}, sprUser.class);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public sprUser getItemByName(String login) {
         try {
            return (sprUser) jdЬcTemplate.queryForObject("SELECT t.id, t.f_login, t.f_pass, t.f_name FROM t_spr_users t WHERE t.f_login = ?", new Object[]{login}, new RowMapper<sprUser>() {
                @Override
                public sprUser mapRow(ResultSet rs, int i) throws SQLException {
                    sprUser res = new sprUser();
                    res.setId(rs.getLong("id"));
                    res.setLogin("f_login");
                    res.setName(rs.getString("f_name"));
                    res.setPassword("f_pass");
                    return res;
                }
            });
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }
    
    @Override
    public List<sprUser> getItemList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<sprUser> getItemList(Long startIndex, Long itemCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long addItem(sprUser Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteItem(sprUser Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateItem(sprUser Item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
