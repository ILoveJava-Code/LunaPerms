package de.ilovejava.commands;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.utils.Utils;
import de.ilovejava.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_LunaUser extends Command {

	public Command_LunaUser(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(!p.hasPermission("*")) {p.sendMessage("§cAttention, unfortunately, this command is not allowed!"); return;}
			if(args.length == 0) {
				p.sendMessage("§e§o§l------------[§6§o§lLunaUser]§e§o§l------------");
				p.sendMessage("§b/LunaUser addGroup [User] [Group]");
				p.sendMessage("§b/LunaUser removeFromGroup [User] [Group]");
				p.sendMessage("§8(§cinactive§8)§b/LunaUser addPermission [User] [Permission]");
				p.sendMessage("§8(§cinactive§8)§b/LunaUser removePermission [User] [Permission]");
				p.sendMessage("§e§o§l------------[§6§o§lLunaUser]§e§o§l------------");
			}else if(args.length == 1) {
				
			}else if(args.length == 2) {
				
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("addgroup")) {
					String group = args[2];
					String user = args[1];
					if(isUUIDExists(user)) {
						if(Utils.getPermissionGroups().containsKey(group)) {
							PermGroup pg = Utils.getPermissionGroups().get(group);
							if(!pg.userInGroup().contains(uuidfetcher.getUUID(user).toString())) {
								pg.addUserToGroup(uuidfetcher.getUUID(user).toString(), pg.permission());
								p.sendMessage("§bYou have the user:§e "+user+"§b to the group:§e "+group+"§b added");
							}else {p.sendMessage("§cAttention, the player:§e "+user+"§c is already in the specify group!");}
						}else {p.sendMessage("§cAttention, the group is not available!");}
					}else {p.sendMessages("§cAttention, the player:§e "+user+" §cdoes not exist!");}
				}else if(args[0].equalsIgnoreCase("removefromgroup")){
					String group = args[2];
					String user = args[1];
					if(isUUIDExists(user)) {
						if(Utils.getPermissionGroups().containsKey(group)) {
							PermGroup pg = Utils.getPermissionGroups().get(group);
							if(pg.userInGroup().contains(uuidfetcher.getUUID(user).toString())) {
								pg.removeUserFormGroup(uuidfetcher.getUUID(user).toString());
								p.sendMessage("§bYou have the user:§e "+user+"§b from the group:§e "+group+"§b removed!");
							}else {p.sendMessage("§cAttention, the player:§e "+user+"§c is not in the specified group");}
						}else {p.sendMessage("§cAttention, the group is not available!");}
					}else {p.sendMessages("§cAttention, the player:§e "+user+" §cdoes not exist!");}
				}
			}
		}
	}

	private boolean isUUIDExists(String name) {
		try {
			uuidfetcher.getUUID(name);
			return true;
		}catch(Exception e) {return false;}
	}
	
}
