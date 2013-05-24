package com.drleinbach.service;

import com.drleinbach.model.Quit;
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
public class PlayerQuitEventService implements Service<Quit, Integer> {

    private final String INSERT_QUIT = "INSERT INTO player_quit_event VALUES(?, ?, ?)";

    @Override
    public Integer insert(Quit arg0) {
        Connection conn = ConnectionFacade.getConnection();

        QueryRunner qRunner = new QueryRunner();

        try {
            qRunner.update(conn, INSERT_QUIT, arg0.getId(), arg0.getPlayerId(), arg0.getQuit());
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            DbUtils.closeQuietly(conn);
        }

        return null;
    }

    @Override
    public Quit get(Integer arg0) {
        Connection conn = ConnectionFacade.getConnection();

        QueryRunner qRunner = new QueryRunner();

//        try {
//            return qRunner.query(conn, INSERT_PLAYER, new BeanHandler<Player>(Player.class), arg0);
//        } catch (SQLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }

        return null;
    }

}
