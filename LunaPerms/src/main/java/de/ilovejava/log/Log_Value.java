package de.ilovejava.log;

import java.util.logging.Level;

import de.ilovejava.utils.Utils;
import net.md_5.bungee.BungeeCord;

public class Log_Value implements Log{

	@Override
	public void SaveTypeNotVali() {
		BungeeCord.getInstance().getLogger().log(Level.INFO, "§cAttention, the memory card is not suitable!");
	}

	@Override
	public void MySQLConTrue() {
		BungeeCord.getInstance().getLogger().log(Level.INFO,"§bThe MySQL connection has been created!");
	}

	@Override
	public void MySQLConFalse() {
		BungeeCord.getInstance().getLogger().log(Level.INFO, "§cThe MySQL connection could not be created!");
	}

	@Override
	public void DisablePlugin() {
		BungeeCord.getInstance().getLogger().log(Level.INFO,"§cThe plugin is now stopped!");
		Utils.getInstance().onDisable();
	}

	@Override
	public void ConnectionWrong() {
		BungeeCord.getInstance().getLogger().log(Level.INFO,"§cAttention, the MySQL connection points to an error!");
	}

	@Override
	public void InsertTables() {
		BungeeCord.getInstance().getLogger().log(Level.INFO,"§bThe MySQL tables are now created!");
	}
	
	@Override
	public void LoadGroup(String name) {
		BungeeCord.getInstance().getLogger().log(Level.INFO,"§bThe group: §e"+name+"§b will be loaded now!");
	}

	@Override
	public void LoadGroupPerm(Integer loads) {
		BungeeCord.getInstance().getLogger().log(Level.INFO, "§b" + loads + " Permission were loaded!");
	}

	@Override
	public void UserGroupLoad(Integer loads) {
		BungeeCord.getInstance().getLogger().log(Level.INFO,"§b"+loads+" User were loaded!");
	}

	@Override
	public void MultiClientConnect(String Name, String ip) {
		BungeeCord.getInstance().getLogger().log(Level.INFO, "§bThe Permission Client:§e "+Name+"§b has connected to the server on the IP :§e " + ip);
	}

	@Override
	public void QueryError(String User, String Class) {
		BungeeCord.getInstance().getLogger().log(Level.INFO, "§cAttention Error in the Query System User-> §4" + User + "§c Class-> §4" + Class);
	}

	@Override
	public void MultiUserLoad(Integer i) {
		BungeeCord.getInstance().getLogger().log(Level.INFO, "§b"+i+" Multiuser were loaded!");
	}
}
