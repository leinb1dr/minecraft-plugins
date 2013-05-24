package com.drleinbach.listeners;

import com.drleinbach.model.Quit;
import com.drleinbach.model.impl.LoginImpl;
import com.drleinbach.model.impl.QuitImpl;
import com.drleinbach.service.Service;
import com.drleinbach.util.PlayerCache;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Daniel
 * Date: 4/23/13
 * Time: 10:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginListener implements Listener {

    private PlayerCache playerCache;
    private Service<LoginImpl, Integer> playerLoginEventService;
    private Service<Quit, Integer> playerQuitEventService;

    public LoginListener(PlayerCache playerCache,
                         Service<LoginImpl, Integer> playerLoginEventService,
                         Service<Quit, Integer> playerQuitEventService) {

        this.playerCache = playerCache;
        this.playerLoginEventService = playerLoginEventService;
        this.playerQuitEventService = playerQuitEventService;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent playerLoginEvent) {
        Integer playerId = playerCache.getPlayerId(playerLoginEvent.getPlayer().getDisplayName());
        Integer loginId = playerLoginEventService.insert(new LoginImpl(playerId));
        playerCache.setLoginId(playerLoginEvent.getPlayer().getDisplayName(), loginId);

    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent playerQuitEvent) {

        Integer playerId = playerCache.getPlayerId(playerQuitEvent.getPlayer().getDisplayName());
        playerQuitEventService
                .insert(new QuitImpl(playerCache.getLoginId(playerQuitEvent.getPlayer().getDisplayName()), playerId));

    }
}
