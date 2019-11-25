package de.ilovejava.lunaperms;

import java.util.ArrayList;
import java.util.HashMap;

import de.ilovejava.autoinit.LoadGroupPermission;
import de.ilovejava.autoinit.LoadMultiUser;
import de.ilovejava.autoinit.LoadPermission;
import de.ilovejava.autoinit.LoadUser;
import de.ilovejava.commands.Command_LunaMultie;
import de.ilovejava.commands.Command_LunaPerms;
import de.ilovejava.commands.Command_LunaUser;
import de.ilovejava.events.Event_Join;
import de.ilovejava.events.Event_Leave;
import de.ilovejava.events.PermissionCheck;
import de.ilovejava.utils.Utils;
import de.ilovejava.values.Update;
import net.md_5.bungee.api.plugin.Plugin;

public class LunaPerms extends Plugin{
	@Override
	public void onEnable() {
		Utils.setInstance(this);
		Utils.setPermissionGroups(new HashMap<>());
		Utils.setUserGroups(new HashMap<>());
		Utils.setMulitUser(new HashMap<>());
		Utils.getMulitUser().put("Test", "AAAA11cc");
		Utils.setClients(new ArrayList<>());
		
		
		new Update();
		
		this.getProxy().getPluginManager().registerCommand(this, new Command_LunaPerms("Lunaperms"));
		this.getProxy().getPluginManager().registerCommand(this, new Command_LunaUser("LunaUser"));
		this.getProxy().getPluginManager().registerCommand(this, new Command_LunaPerms("LP"));
		this.getProxy().getPluginManager().registerCommand(this, new Command_LunaUser("LU"));
		this.getProxy().getPluginManager().registerCommand(this, new Command_LunaMultie("lunacon"));
		
		
		this.getProxy().getPluginManager().registerListener(this, new Event_Join());
		this.getProxy().getPluginManager().registerListener(this, new Event_Leave());
		this.getProxy().getPluginManager().registerListener(this, new PermissionCheck());
		
		if(Utils.MySQlConnect) {
			new LoadPermission();
			new LoadGroupPermission();
			new LoadUser();
			new LoadMultiUser();
		}
	}
	
	@Override
	public void onDisable() {
		
	}
}
