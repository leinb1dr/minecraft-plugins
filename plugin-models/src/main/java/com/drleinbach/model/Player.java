package com.drleinbach.model;

/**
 * This interface represents the database table that holds player
 * information.
 * <p/>
 * Created: 4/26/13
 *
 * @author Daniel
 */
public interface Player {

    /**
     * Set the screen name of the player.
     *
     * @param screenName - The name of the player
     */
    void setScreenName(String screenName);

    /**
     * Get the numeric id for the record. This id is the primary
     * key that represents the given record.
     *
     * @return The id for the record
     */
    Integer getId();

    /**
     * Get the screen name representing the player. This is the name
     * of the player for the record.
     *
     * @return - The screen name of the player to which the record belongs
     */
    String getScreenName();

}
