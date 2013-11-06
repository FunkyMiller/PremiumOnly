package io.github.FunkyMiller.PremiumOnly;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public final class PremiumOnlyPlugin 
	extends JavaPlugin 
	implements Listener 
{
	
	public final Logger logger = Logger.getLogger("Minecraft");

	public ArrayList<Player> PremiumPlayerList = new ArrayList();
	public ArrayList<Player> HidingPlayerList = new ArrayList();

	@Override
	public void onEnable(){
		this.logger.info("onEnable has been invoked");
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (PermissionsEx.getUser(p).inGroup("Premium")) {	// Replace with code to detect if Player p is a premium player
				PremiumPlayerList.add(p);
			}
		}
	}
	
	@Override
	public void onDisable(){
		this.logger.info("onDisable has been invoked");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("hidenonpremplayers")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player");
				return false;
			} else {
				if (!PremiumPlayerList.contains(sender)) {
					this.logger.info(sender.getName() + " could not hide non-premium players as they are not a premium player");
					sender.sendMessage(ChatColor.RED + "You are not a Premium Player (donator), you cannot hide non-premium players");
					return false;
				} else {
					if (HidingPlayerList.contains((Player) sender)) {
						this.logger.info(sender.getName() + " is already hiding non-premium players");
						sender.sendMessage(ChatColor.RED + "You are already hiding non-premium players, use /shownonpremplayers to show all players again");
						return false;
					} else {
						this.logger.info(sender.getName() + " is hiding non-premium players");
						HidingPlayerList.add((Player) sender);
						for (Player p: Bukkit.getOnlinePlayers()) {
							if (!PremiumPlayerList.contains(p)) {
								((Player) sender).hidePlayer(p);
							} else {
								((Player) sender).showPlayer(p);
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}
}
