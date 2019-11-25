package de.ilovejava.autoinit;

import java.sql.ResultSet;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.log.Log_Value;
import de.ilovejava.utils.Utils;

public class LoadUser extends Log_Value{
	public LoadUser() {
		int i = 0;
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LunaPerms_User");
		try {
			while(rs.next()) {
				if(Utils.getPermissionGroups().containsKey(rs.getString("PermGroup"))) {
					PermGroup pg = Utils.getPermissionGroups().get(rs.getString("PermGroup"));
					pg.setUnserInGroup(rs.getString("UUID"));
					Utils.getUserGroups().put(rs.getString("UUID"), pg);
					i++;
				}else {Utils.getMysql().update("UPDATE LunaPerms_User SET PermGroup='default' WHERE UUID='"+rs.getString("UUID")+"'");}
			}
			rs.close();
		}catch(Exception e) {e.printStackTrace();}
		this.UserGroupLoad(i);
	}
}
