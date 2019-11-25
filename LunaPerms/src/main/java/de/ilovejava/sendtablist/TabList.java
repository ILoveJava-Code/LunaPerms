package de.ilovejava.sendtablist;

import java.net.Socket;

import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class TabList extends Query{
	public TabList(Socket c) {
		for(String s : Utils.getPermissionGroups().keySet()) {
			this.updateGroupTab(Utils.getPermissionGroups().get(s).Tablist(), s, c);
		}
		this.TablistSendEnd(c);
	}
}
