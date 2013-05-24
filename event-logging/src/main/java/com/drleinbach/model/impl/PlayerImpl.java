package com.drleinbach.model.impl;

import com.drleinbach.model.Player;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 4/23/13
 * @author Daniel
 */
public class PlayerImpl implements Serializable, Player {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(PlayerImpl.class);

    Integer id;
    String screenName;

    public PlayerImpl() {
        id = null;
        screenName = null;
    }



    /**
     * Parameterized constructor that creates a new player and
     * initializes the screen name.
     *
     * @param screenName
     */
    public PlayerImpl(String screenName) {
        this.screenName = screenName;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the database id of the player
     *
     * @return id of the player
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Get the screen name of the player.
     *
     * @return screen name of the player
     */
    @Override
    public String getScreenName() {
        return screenName;
    }

    /**
     * Set the Screen name of the player
     *
     * @param screenName - Screen name of the player
     */
    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public static class PlayerResultSetHandler implements ResultSetHandler<Player> {
        @Override
        public Player handle(ResultSet resultSet) throws SQLException {
            // Check for values
            LOGGER.debug("isBeforeFirst: " + resultSet.isBeforeFirst());
            if (resultSet.isBeforeFirst() && !resultSet.next()) {
                return null;
            }



            Player player = new PlayerImpl();

            Field[] fields = PlayerImpl.class.getDeclaredFields();
            for (Field field : fields) {

                if ((field.getModifiers() & 8) == 0) {
                    try {
                        field.setAccessible(true);
                        field.set(player, resultSet.getObject(field.getName()));
                        field.setAccessible(false);
                    } catch (Exception e) {
                        LOGGER.error("Exception has occurred", e);
                        return null;
                    } finally {
                        LOGGER.debug("Finished parsing player");
                    }
                }
            }

            return player;
        }
    }

    public static class PlayerListResultSetHandler implements ResultSetHandler<List<Player>> {
        @Override
        public List<Player> handle(ResultSet resultSet) throws SQLException {
            // Check for values

            List<Player> players = new ArrayList<Player>();
            PlayerResultSetHandler playerHandler = new PlayerResultSetHandler();

            while (resultSet.next()){
                players.add(playerHandler.handle(resultSet));
            }

            return players;
        }
    }
}
