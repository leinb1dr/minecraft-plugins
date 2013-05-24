package com.drleinbach.service;

import com.drleinbach.model.Login;
import com.drleinbach.model.impl.LoginImpl;
import com.drleinbach.util.ConnectionFacade;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 4/23/13
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerLoginEventService implements Service<LoginImpl, Integer> {

    private final String INSERT_LOGIN = "INSERT INTO player_login_event(player_id, login) VALUES(?, ?)";
    private final String SELECT_ID = "SELECT player_login_event_id as id," +
            "player_id as playerId," +
            "login as login " +
            "FROM player_login_event " +
            "WHERE player_id = ? AND login = ?";

    @Override
    public Integer insert(LoginImpl arg0) {
        Connection conn = ConnectionFacade.getConnection();

        QueryRunner qRunner = new QueryRunner();
        Login login = null;
        try {
            qRunner.update(conn, INSERT_LOGIN, arg0.getPlayerId(), arg0.getLogin());
            login = qRunner.query(conn, SELECT_ID, new LoginImpl.LoginResultSetHandler(), arg0.getPlayerId(),
                    arg0.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }

        return (login != null) ? login.getId() : null;
    }

    @Override
    public LoginImpl get(Integer arg0) {
//        Connection conn = ConnectionFacade.getConnection();
//
//        QueryRunner qRunner = new QueryRunner();

//        try {
//            return qRunner.query(conn, INSERT_PLAYER, new BeanHandler<Player>(Player.class), arg0);
//        } catch (SQLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }

        return null;
    }

}
