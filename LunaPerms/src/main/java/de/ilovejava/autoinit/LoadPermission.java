package de.ilovejava.autoinit;

import java.sql.ResultSet;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.log.Log_Value;
import de.ilovejava.utils.Utils;

public class LoadPermission extends Log_Value{
	public LoadPermission() {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LunaPerms_Group");
		try {
			while(rs.next()) {
				new PermGroup(rs.getInt("id"));
				this.LoadGroup(rs.getString("Name"));
			}
			rs.close();
		}catch(Exception e) {e.printStackTrace();}
	}
}
