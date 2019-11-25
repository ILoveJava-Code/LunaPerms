package de.ilovejava.autoinit;

import java.sql.ResultSet;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.log.Log_Value;
import de.ilovejava.utils.Utils;

public class LoadGroupPermission extends Log_Value{
	public LoadGroupPermission() {
		int i = 0;
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LunaPerms_Permission");
		try {
			while(rs.next()) {
				String group = rs.getString("PermGroup");
				if(Utils.getPermissionGroups().containsKey(group)) {
					PermGroup pg = Utils.getPermissionGroups().get(group);
					pg.setPermission(rs.getString("Permission"));
					i++;
				}else {Utils.getMysql().update("DELETE FROM LunaPerms_Permission WHERE id='"+rs.getInt("id")+"'");}
			}
			rs.close();
		}catch(Exception e) {e.printStackTrace();}
		this.LoadGroupPerm(i);
	}
}
