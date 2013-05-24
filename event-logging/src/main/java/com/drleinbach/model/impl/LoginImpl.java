package com.drleinbach.model.impl;

import com.drleinbach.model.Login;
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
public class LoginImpl implements Serializable, Login {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(PlayerImpl.class);

    Integer id;
    Integer playerId;
    Timestamp login;

    public LoginImpl() {
    }

    public LoginImpl(Integer playerId){
        this.playerId = playerId;
        this.login = new Timestamp(System.currentTimeMillis());
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
    public Timestamp getLogin() {
        return login;
    }

    @Override
    public void setLogin(Timestamp login) {
        this.login = login;
    }

    public static class LoginResultSetHandler implements ResultSetHandler<Login> {
        @Override
        public Login handle(ResultSet resultSet) throws SQLException {
            // Check for values
            if (resultSet.isBeforeFirst() && !resultSet.next()) {
                return null;
            }

            Login login = new LoginImpl();

            Field[] fields = LoginImpl.class.getDeclaredFields();
            for (Field field : fields) {

                if ((field.getModifiers() & 8) == 0) {
                    try {
                        if(field.isAccessible())
                            field.setAccessible(true);
                        field.set(login, resultSet.getObject(field.getName()));
                    } catch (Exception e) {
                        LOGGER.error("Exception has occurred", e);
                        return null;
                    } finally {
                        LOGGER.debug("Finished parsing player");
                    }
                }
            }

            return login;
        }
    }

    public static class LoginListResultSetHandler implements ResultSetHandler<List<Login>> {
        @Override
        public List<Login> handle(ResultSet resultSet) throws SQLException {
            // Check for values

            List<Login> logins = new ArrayList<Login>();
            LoginResultSetHandler playerHandler = new LoginResultSetHandler();

            while (resultSet.next()){
                logins.add(playerHandler.handle(resultSet));
            }

            return logins;
        }
    }
}
