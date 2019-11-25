package de.ilovejava.defaultcreate;

import de.ilovejava.utils.Utils;

import java.sql.ResultSet;

public class DefaultCreate {
	public DefaultCreate(String uuid) {
		if(!isUserExists(uuid)) {
			Utils.getMysql().update("INSERT INTO LunaPerms_User(UUID,PermGroup) VALUES('"+uuid+"','default')");
			Utils.getUserGroups().put(uuid,Utils.getPermissionGroups().get("default"));
		}
	}
	
	
	public boolean isUserExists(String uuid) {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LunaPerms_User WHERE UUID='"+uuid+"'");
		try {
			if(rs.next()) {
				return true;
			}
			rs.close();
		}catch(Exception e) {e.printStackTrace();}
		return false;
	}
}
