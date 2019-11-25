package de.ilovejava.commands;

import java.util.ArrayList;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.utils.Utils;
import de.ilovejava.uuid.uuidfetcher;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class Command_LunaPerms extends Command {

	public Command_LunaPerms(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("*")) {p.sendMessage("§cAttention, unfortunately, this command is not allowed!"); return;}
				p.sendMessage("§e§o§l------------[§6§o§lLunaPerms]§e§o§l------------");
				p.sendMessage("§b/LunaPerms create [Groupname]");
				p.sendMessage("§b/LunaPerms setPrefix [Groupname] [Name]");
				p.sendMessage("§b/LunaPerms setChatColor [Groupname] [Colorcode]");
				p.sendMessage("§b/LunaPerms setTablist [Groupname] [Name]");
				p.sendMessage("§b/LunaPerms setTagid [Groupname] [ID]");
				p.sendMessage("§b/LunaPerms addPermission [Groupname] [Permission]");
				p.sendMessage("§b/LunaPerms removePermission [Groupname] [Permission]");
				p.sendMessage("§e§o§l------------[§6§o§lLunaPerms]§e§o§l------------");
			}else if(args.length == 1) {
				
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("create")) {
					String name = args[1];
					PermGroup pg = new PermGroup(name);
					pg.create();
					p.sendMessage("§bThe group was created successfully!");
				}
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("setprefix")) {
					String group = args[1];
					String name = args[2];
					if(Utils.getPermissionGroups().containsKey(group)) {
						PermGroup pg = Utils.getPermissionGroups().get(group);
						pg.setPrefix(name);
						pg.save();
						p.sendMessage("§bThe prefix of the group: §e"+group+"§b is now: §e" + ChatColor.translateAlternateColorCodes('&', name));
					}else {p.sendMessage("§cAttention, the group is not available!");}
				}else if(args[0].equalsIgnoreCase("settablist")) {
					String group = args[1];
					String name = args[2];
					if(Utils.getPermissionGroups().containsKey(group)) {
						PermGroup pg = Utils.getPermissionGroups().get(group);
						pg.setTabString(name);
						pg.save();
						p.sendMessage("§bThe tablist of the group: §e"+group+"§b is now: §e" + ChatColor.translateAlternateColorCodes('&', name));
					}else {p.sendMessage("§cAttention, the group is not available!");}
				}else if(args[0].equalsIgnoreCase("settagid")) {
					String group = args[1];
					String name = args[2];
					if(Utils.getPermissionGroups().containsKey(group)) {
						PermGroup pg = Utils.getPermissionGroups().get(group);
						pg.setTabList(Integer.valueOf(name));
						pg.save();
						p.sendMessage("§bThe position of the group: §e"+group+"§bin the tablist is now:§e " + name);
					}else {p.sendMessage("§cAttention, the group is not available!");}
				}else if(args[0].equalsIgnoreCase("addpermission")) {
					String group = args[1];
					String Permission = args[2];
					if(Utils.getPermissionGroups().containsKey(group)) {
						PermGroup pg = Utils.getPermissionGroups().get(group);
						if(!pg.hasPermission(Permission)) {
							pg.addPermission(Permission);
							p.sendMessage("§bYou have the permission:§e "+Permission+"§b successfully added to the group:§e"+group+"!");
						}else {p.sendMessage("§cAttention, the specified permission is already registered in this group!");}
					}else {p.sendMessage("§cAttention, the group is not available!");}
				}else if(args[0].equalsIgnoreCase("removepermission")) {
					String group = args[1];
					String Permission = args[2];
					if(Utils.getPermissionGroups().containsKey(group)) {
						PermGroup pg = Utils.getPermissionGroups().get(group);
						if(pg.hasPermission(Permission)) {
							pg.removePermission(Permission);
							p.sendMessage("§bYou have the permission:§e "+Permission+"§b from the group:§e"+group+"§b removed!!");
						}else {p.sendMessage("§cAttention, the specified group did not save the specified permission!!");}
					}else {p.sendMessage("§cAttention, the group is not available!");}
				}else if(args[0].equalsIgnoreCase("setChatColor")) {
					String group = args[1];
					String color = args[2];
					if(Utils.getPermissionGroups().containsKey(group)) {
						PermGroup pg = Utils.getPermissionGroups().get(group);
						pg.setMessageColor(color);
						pg.save();
						p.sendMessage("§bThe new chat color of the group: §e"+group+"§b looks like this: "+ChatColor.translateAlternateColorCodes('&', color)+"test");
					}else {p.sendMessage("§cAttention, the group is not available!");}
				}
			}
		}else {
			ConsoleCommandSender ccs = (ConsoleCommandSender) sender;
			if(args.length == 0) {
				ccs.sendMessage("§blp admin [Name]");
			}else if(args.length == 1) {
				
			}else if(args.length == 2) {
				String user = uuidfetcher.getUUID(args[1]).toString();
				Utils.getPermissionGroups().get("Admin").addUserToGroup(user, new ArrayList<String>());
				ccs.sendMessage("§cThe player is now Admin!");
			}
		}
	}

}
