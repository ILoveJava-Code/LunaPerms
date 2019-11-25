package de.ilovejava.user;

import java.net.Socket;

import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class SendUser extends Query{
	public SendUser(Socket c) {
		for(String s : Utils.getPermissionGroups().keySet()) {
			String user = Utils.getPermissionGroups().get(s).userInGroup().toString().replace("[", "").replace("]", "");
			this.sendUserTOGroup(user, s, c);
		}
		this.UserSendEnd(c);
	}
}
