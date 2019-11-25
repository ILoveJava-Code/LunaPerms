package de.ilovejava.multi.permission;

import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class AddPermission extends Query{
	public AddPermission(String permission, String group) {
		for(Client_Stock c : Utils.getClients()) {
			this.UpdateAddPermission(c.client(), group, permission);
		}
	}
}
