package com.drleinbach.model.impl;

import com.drleinbach.model.Quit;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 4/23/13
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuitImpl implements Serializable, Quit {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(PlayerImpl.class);

    Integer id;
    Integer playerId;
    Timestamp quit;

    public QuitImpl(){
    }

    public QuitImpl(Integer id, Integer playerId){
        this.id = id;
        this.playerId = playerId;
        this.quit = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getPlayerId() {
        return playerId;
    }

    @Override
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    @Override
    public Timestamp getQuit() {
        return quit;
    }

    @Override
    public void setQuit(Timestamp quit) {
        this.quit = quit;
    }

    public static class QuitResultSetHandler implements ResultSetHandler<Quit> {
        @Override
        public Quit handle(ResultSet resultSet) throws SQLException {
            // Check for values
            if (resultSet.isBeforeFirst() && !resultSet.next()) {
                return null;
            }

            Quit quit = new QuitImpl();

            Field[] fields = QuitImpl.class.getDeclaredFields();
            for (Field field : fields) {

                if ((field.getModifiers() & 8) == 0) {
                    try {
                        field.set(quit, resultSet.getObject(field.getName()));
                    } catch (Exception e) {
                        LOGGER.error("Exception has occurred", e);
                        return null;
                    } finally {
                        LOGGER.debug("Finished parsing player");
                    }
                }
            }

            return quit;
        }
    }

    public static class QuitListResultSetHandler implements ResultSetHandler<List<Quit>> {
        @Override
        public List<Quit> handle(ResultSet resultSet) throws SQLException {
            // Check for values

            List<Quit> quits = new ArrayList<Quit>();
            QuitResultSetHandler playerHandler = new QuitResultSetHandler();

            while (resultSet.next()){
                quits.add(playerHandler.handle(resultSet));
            }

            return quits;
        }
    }
}
