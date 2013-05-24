package com.drleinbach.util;

import com.drleinbach.model.Player;
import com.drleinbach.model.impl.CacheMetadata;
import com.drleinbach.model.impl.PlayerImpl;
import com.drleinbach.service.Service;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 4/23/13
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerCache {

    private static final Logger LOGGER = Logger.getLogger(PlayerCache.class);

    private Map<String, CacheMetadata> playerCache;
    private Service<Player, String> playerDao;

    public PlayerCache(Service<Player, String> playerDao){
        this.playerCache = new HashMap<String, CacheMetadata>();
        this.playerDao = playerDao;
    }

    public Integer getLoginId(String screen_name){
        Integer loginId = playerCache.get(screen_name).getLoginId();
        LOGGER.debug("Getting database login id for " + screen_name + " value is: " + loginId);
        return loginId;
    }

    public void setLoginId(String screen_name, Integer loginId){
        LOGGER.debug("Setting database login id for " + screen_name + " value is: " + loginId);
        playerCache.get(screen_name).setLoginId(loginId);
    }

    public Integer getPlayerId(String screen_name){

        LOGGER.debug("Get database id for player " + screen_name);

        if(playerCache.containsKey(screen_name)){
            LOGGER.debug("Found screen name returning id");
            return playerCache.get(screen_name).getPlayerId();
        } else {
            LOGGER.debug("No id cached");
            Player p = playerDao.get(screen_name);
            Integer id = 0;
            if (p == null) {
                LOGGER.debug("User is not in database, adding ...");
                id = playerDao.insert(new PlayerImpl(screen_name));
            } else {
                id = p.getId();
                LOGGER.debug("Found user in database with id " + id);
            }

            LOGGER.debug("Adding user and id to cache");
            playerCache.put(screen_name, new CacheMetadata(id));
            return id;
        }
    }
}
