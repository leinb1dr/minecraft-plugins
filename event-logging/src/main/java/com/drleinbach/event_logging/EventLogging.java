package com.drleinbach.event_logging;


import com.drleinbach.listeners.LoginListener;
import com.drleinbach.service.PlayerLoginEventService;
import com.drleinbach.service.PlayerQuitEventService;
import com.drleinbach.service.PlayerService;
import com.drleinbach.util.PlayerCache;
import org.apache.commons.dbcp.BasicDataSource;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * This is a plugin to log events that occur within the minecraft
 * world. These events will be written to a database of some kind
 * to be used later.
 */
public final class EventLogging extends JavaPlugin {

    private final Logger LOGGER = getLogger();


    @Override
    public void onEnable() {
        getLogger().info("onEnabled Invoked");
        getLogger().info(EventLogging.class.getClassLoader().getResourceAsStream("applicationContext.xml").toString());

        BasicDataSource ds;

        getServer().getPluginManager().registerEvents(
                new LoginListener(
                        new PlayerCache(new PlayerService()),
                        new PlayerLoginEventService(),
                        new PlayerQuitEventService()),
                this);
    }

    @Override
    public void onDisable() {
        getLogger().info("onEnabled Invoked");
        HandlerList.unregisterAll(this);
    }


}
