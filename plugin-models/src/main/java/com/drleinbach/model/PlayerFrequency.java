package com.drleinbach.model;

import java.sql.Timestamp;

/**
 * Created: 5/3/13
 *
 * @author Daniel
 */
public interface PlayerFrequency {

    void setLoginTime(Timestamp loginTime);

    void setNumberOfItems(Integer numberOfItems);

    Timestamp getLoginTime();

    Integer getNumberOfItems();

}
