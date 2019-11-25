package de.ilovejava.sendprefix;

import java.net.Socket;

import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class SendPrefix extends Query{
	public SendPrefix(Socket c) {
		for(String s : Utils.getPermissionGroups().keySet()) {
			this.updateGroupPrefix(Utils.getPermissionGroups().get(s).Prefix(), s, c);
		}
		this.PrefixSendEnd(c);
	}
}
