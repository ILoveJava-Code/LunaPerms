package de.ilovejava.config;

import java.io.File;

import de.ilovejava.configuration.file.YamlConfiguration;
import de.ilovejava.log.Log_Value;

public class Config_Values extends Log_Value implements Config{

	private String SaveType;
	private String Host;
	private String User;
	private String Password;
	private String Database;
	private Integer Port;
	private Boolean MUse;
	private Integer MulitPort;
	
	private File f;
	YamlConfiguration cfg;
	
	public Config_Values() {
		this.f = new File("plugins/LunaPerms","Config.yml");
		this.cfg = YamlConfiguration.loadConfiguration(f);
		this.load();
	}
	@Override
	public String SaveType() {
		return this.SaveType;
	}

	@Override
	public String MySQLHost() {
		return this.Host;
	}

	@Override
	public String MySQLUser() {
		return this.User;
	}

	@Override
	public String MySQLPassword() {
		return this.Password;
	}

	@Override
	public String MySQLDataBase() {
		return this.Database;
	}

	@Override
	public Integer MySQlPort() {
		return this.Port;
	}

	@Override
	public boolean Multiuse() {
		return this.MUse;
	}

	@Override
	public void load() {
		this.isSet("Config.Save", "MYSQL");
		this.isSet("Config.MySQL.Host", "Host");
		this.isSet("Config.MySQL.User", "User");
		this.isSet("Config.MySQL.Password", "Password");
		this.isSet("Config.MySQL.Database", "Database");
		this.isSet("Config.MySQL.Port", 3306);
		this.isSet("Config.Multiuse", true);
		this.isSet("Config.MulitPort", 1337);
		
		this.setSaveType(this.cfg.getString("Config.Save"));
		this.setMySQLHost(this.cfg.getString("Config.MySQL.Host"));
		this.setMySQLUser(this.cfg.getString("Config.MySQL.User"));
		this.setMySQLPassword(this.cfg.getString("Config.MySQL.Password"));
		this.setMySQLDataBase(this.cfg.getString("Config.MySQL.Database"));
		this.setMultiuse(this.cfg.getBoolean("Config.Multiuse"));
		this.setMySQLPort(this.cfg.getInt("Config.MySQL.Port"));
		this.setMultiPort(this.cfg.getInt("Config.MulitPort"));
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isSet(String s, Object obj) {
		if(!this.cfg.isSet(s)) {
			this.cfg.set(s, obj);
			try {this.cfg.save(this.f);}catch(Exception e) {e.printStackTrace();}
		}
	}
	
	@Override
	public void setSaveType(String s) {
		this.SaveType = s;
	}

	@Override
	public void setMySQLHost(String s) {
		this.Host = s;
	}

	@Override
	public void setMySQLUser(String s) {
		this.User = s;
	}

	@Override
	public void setMySQLPassword(String s) {
		this.Password = s;
	}

	@Override
	public void setMySQLDataBase(String s) {
		this.Database = s;
	}

	@Override
	public void setMySQLPort(Integer i) {
		this.Port = i;
	}

	@Override
	public void setMultiuse(Boolean b) {
		this.MUse = b;
	}
	@Override
	public Integer MultiPort() {
		return this.MulitPort;
	}
	@Override
	public void setMultiPort(Integer i) {
		this.MulitPort = i;
	}

}
