package io.github.FunkyMiller.PremiumOnly;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerListener implements Listener {
	private final PremiumOnlyPlugin plugin;
	
	public PlayerListener(PremiumOnlyPlugin instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (PermissionsEx.getUser(event.getPlayer()).inGroup("Premium")) {
			plugin.PremiumPlayerList.add(event.getPlayer());
		} else {
			for (Player p : plugin.HidingPlayerList) {
				p.hidePlayer(event.getPlayer());
			}
		}
	}
	
}
