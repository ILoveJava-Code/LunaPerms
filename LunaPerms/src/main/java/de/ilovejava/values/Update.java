package de.ilovejava.values;

import java.sql.ResultSet;

import de.ilovejava.config.Config_Values;
import de.ilovejava.multi.Server;
import de.ilovejava.mysql.MySQL;
import de.ilovejava.utils.Utils;

public class Update extends Config_Values{
	public Update() {
		switch(this.SaveType()) {
			case "CONFIG":
				
				break;
			case "MYSQL":
				Utils.setMysql(new MySQL(this.MySQLHost(), this.MySQLDataBase(), this.MySQLUser(), this.MySQLPassword(), this.MySQlPort()));
				if(Utils.getMySQlConnect()) {
					this.InsertTables();
					Utils.getMysql().update("CREATE TABLE IF NOT EXISTS LunaPerms_Group(id MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),Name varchar(123), Prefix varchar(123), Tablist varchar(123), TagID int, MessageColor varchar(123))");
					Utils.getMysql().update("CREATe TABLE IF NOT EXISTS LunaPerms_Permission(id MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),GroupID int, Permission varchar(333), PermGroup varchar(123))");
					Utils.getMysql().update("CREATe TABLE IF NOT EXISTS LunaPerms_User(id MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (id), UUID varchar(123), PermGroup varchar(123))");
					if(!isDefaultGroupExists()) {
						Utils.getMysql().update("INSERT INTO LunaPerms_Group(Name,Prefix,Tablist,TagID,MessageColor) VALUES('default','&2Player','&2S','100','&f')");
						Utils.getMysql().update("INSERT INTO LunaPerms_Group(Name,Prefix,Tablist,TagID,MessageColor) VALUES('Admin','&cAdmin','&2S','0','&f')");
						Utils.getMysql().update("INSERT INTO LunaPerms_Permission(GroupID,Permission,PermGroup) VALUES('0','*','Admin')");
					}
				}else {this.ConnectionWrong(); Utils.getInstance().onDisable();}
				break;
			default:
				this.SaveTypeNotVali();
				break;
		}
		
		
		if(this.Multiuse()) {
			Server s = new Server(this.MultiPort());
			s.run();
		}
	}
	
	public boolean isDefaultGroupExists() {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LunaPerms_Group WHERE Name='default'");
		try {
			if(rs.next()) {
				return true;
			}
			rs.close();
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}
}
