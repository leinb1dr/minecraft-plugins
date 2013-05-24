package com.drleinbach.model;

import java.sql.Timestamp;

/**
 * This is an interface that represents the database
 * view which holds data for all of the players login
 * records.
 * <p/>
 * Created: 5/4/13
 *
 * @author Daniel
 */
public interface AllPlayerStatus {


    /**
     * Set the Screen name for the player's login
     *
     * @param screenName - Screen Name representing the player
     */
    void setScreenName(String screenName);

    /**
     * Set the login for the player. This is when the player started
     * playing.
     *
     * @param login - When the player started playing
     */
    void setLogin(Timestamp login);

    /**
     * Set the quit time for the player. This is when the player
     * stopped playing.
     *
     * @param quit - When the player stopped playing
     */
    void setQuit(Timestamp quit);

    /**
     * Get the screen name representing the player. This is the name
     * of the player for the record.
     *
     * @return - The screen name of the player to which the record belongs
     */
    String getScreenName();

    /**
     * Get the login time, this is when the player started playing.
     *
     * @return - The time the player started playing.
     */
    Timestamp getLogin();

    /**
     * Get the quit time, this is when the player stopped playing.
     *
     * @return - The time the player stopped playing
     */
    Timestamp getQuit();
}
