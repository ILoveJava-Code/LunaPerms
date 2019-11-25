package de.ilovejava.commands;

import java.io.File;
import java.util.Random;

import de.ilovejava.configuration.file.YamlConfiguration;
import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_LunaMultie extends Command {

	public Command_LunaMultie(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(!p.hasPermission("*")) {p.sendMessage("§cAttention, unfortunately, the command is not released for you!"); return;}
			if(args.length == 0) {
				p.sendMessage("§b/LunaMulti create [User]");
				p.sendMessage("§b/LunaMulti delete [User]");
			}else if(args.length == 1) {
				
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("create")) {
					String user = args[1];
					File f = new File("plugins/LunaPerms/ConUser", user+".user");
					if(!f.exists()) {
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
						String code = createVeryCode();
						cfg.set("Config.Password", code);
						cfg.set("Config.Name", user);
						try {cfg.save(f);}catch(Exception e) {e.printStackTrace();}
						p.sendMessage("§cYou have created a Connection User Name:§e "+user+"§c Password: §e"+code+"§c Attention, remember this password!");
						Utils.getMulitUser().put(user, code);
					}else {p.sendMessage("§cAttention, the user already exists");}
				}else if(args[0].equalsIgnoreCase("delete")) {
					String user = args[1];
					File f = new File("plugins/LunaPerms/ConUser", user+".user");
					if(f.exists()) {
						f.delete();
						p.sendMessage("§The user: §e"+user+"§c has been deleted!");
						for(Client_Stock cs : Utils.getClients()) {
							if(cs.Name().equals(user)) {
								try{cs.client().close();}catch(Exception e) {e.printStackTrace();}
							}
						}
					}else {p.sendMessage("§cAttention, the user is not available");}
				}
			}
		}
	}

	public String createVeryCode() {
		int length = 8;
		String values = "ABCDEFGHIJKLMNOPQRSTUVWXYC1234567890abcdefghijklmnopqrstuvxyz";
		char[] carray = values.toCharArray();
		String id = null;
		Random rnd = new Random();
		for(int i = 0; i<length; i++) {
			id = id+carray[rnd.nextInt(carray.length)];
		}
		id = id.replace("null", "");
		return id;
	}
	
}
