package de.ilovejava.sendpermission;

import java.net.Socket;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class SendPermission extends Query{
	public SendPermission(Socket c) {
		for(String s : Utils.getPermissionGroups().keySet()) {
			PermGroup pg = Utils.getPermissionGroups().get(s);
			String permission = pg.permission().toString().replace("[", "").replace("]", "");
			this.addPermission(permission, s, c);
		}
		this.PermissionSendEnd(c);
	}
}
