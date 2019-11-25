package de.ilovejava.sendtagid;

import java.net.Socket;

import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class TagID extends Query{
	public TagID(Socket c) {
		for(String s : Utils.getPermissionGroups().keySet()) {
			this.updateGroupTagID(String.valueOf(Utils.getPermissionGroups().get(s).tabid()), s, c);
		}
		this.TagIDSendEnd(c);
	}
}
