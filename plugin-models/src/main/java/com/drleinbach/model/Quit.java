package com.drleinbach.model;

import java.sql.Timestamp;

/**
 * Created: 4/26/13
 *
 * @author Daniel
 */
public interface Quit {

    /**
     * Set the id of the quit record so that it matches a
     * login record. This id is the primary key and is the
     * record number from the database.
     *
     * @param id - Id representing the record
     */
    void setId(Integer id);

    /**
     * Set the player id for the login record. This id is a
     * foreign key of the player table and should
     *
     * @param playerId - Id of the player from the player table
     */
    void setPlayerId(Integer playerId);

    /**
     * Set the quit time for the player. This is when the player
     * stopped playing.
     *
     * @param quit - Time when the player started playing
     */
    void setQuit(Timestamp quit);

    /**
     * Get the id of the quit record. This id is the primary key
     * and is the record number from the database.
     *
     * @return The primary key, this id matches an id from the login table
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
     * Get the time when the player quit in. This quit value is when
     * the player stopped playing.
     *
     * @return The time when the player stopped playing.
     */
    Timestamp getQuit();


}
