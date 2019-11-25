package de.ilovejava.multi.client;

import java.net.Socket;

import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class SendGroups extends Query{
	public SendGroups(Socket c) {
		for(String name : Utils.getPermissionGroups().keySet()) {
			this.sendGroups(name, c);
		}
		this.GroupSendEnd(c);
	}
}
