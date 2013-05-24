package com.drleinbach.model;

import java.sql.Timestamp;

/**
 * This interface represents the database table that holds player
 * login data.
 * <p/>
 * Created: 4/26/13
 *
 * @author Daniel
 */
public interface Login {

    /**
     * Set the player id for the login record. This id is a
     * foreign key of the player table and should
     *
     * @param playerId - Id of the player from the player table
     */
    void setPlayerId(Integer playerId);

    /**
     * Set the login time for the player. This is when the player
     * started playing.
     *
     * @param login - Time when the player started playing
     */
    void setLogin(Timestamp login);

    /**
     * Gets the id of the login record. This id is the primary key
     * and is the record number from the database.
     *
     * @return Id representing the record
     */
    Integer getId();

    /**
     * Get the player id which is a numeric value representing a
     * player in the player table.
     *
     * @return Id of a player to which this record belongs
     */
    Integer getPlayerId();

    /**
     * Get the time when the player logged in. This login value is when
     * the player started playing.
     *
     * @return The time when the player started playing.
     */
    Timestamp getLogin();


}
