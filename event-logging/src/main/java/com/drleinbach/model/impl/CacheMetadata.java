package com.drleinbach.model.impl;

import org.apache.log4j.Logger;

/**
 * Created: 4/26/13
 *
 * @author Daniel
 */
public class CacheMetadata {

    private static final Logger LOGGER = Logger.getLogger(CacheMetadata.class);

    private Integer playerId;
    private Integer loginId;

    public CacheMetadata() {
    }

    public CacheMetadata(Integer playerId) {
        this.playerId = playerId;
    }

    public CacheMetadata(Integer playerId, Integer loginId) {
        this.playerId = playerId;
        this.loginId = loginId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }
}
