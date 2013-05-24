package com.drleinbach.service;

import com.drleinbach.model.Player;
import com.drleinbach.model.impl.PlayerImpl;
import com.drleinbach.util.ConnectionFacade;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 4/24/13
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerService implements Service<Player, String> {

    private static final Logger LOGGER = Logger.getLogger(PlayerService.class.getName());

    private final String SELECT_PLAYER = "SELECT player_id as id, " +
            "screen_name as screenName " +
            "FROM player WHERE screen_name = ?";
    private final String INSERT_PLAYER = "INSERT INTO player(screen_name) VALUES(?)";


    @Override
    public Integer insert(Player arg0) {
        Connection conn = ConnectionFacade.getConnection();

        QueryRunner qRunner = new QueryRunner();
        Integer value = 0;

        try {
            qRunner.update(conn, INSERT_PLAYER, arg0.getScreenName());
            value = get(arg0.getScreenName()).getId();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            DbUtils.closeQuietly(conn);
        }

        return value;
    }

    @Override
    public Player get(String arg0) {
        Connection conn = ConnectionFacade.getConnection();

        QueryRunner qRunner = new QueryRunner();

        try {
            return qRunner.query(conn, SELECT_PLAYER, new PlayerImpl.PlayerResultSetHandler(), arg0);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            DbUtils.closeQuietly(conn);
        }

        return null;
    }
}
