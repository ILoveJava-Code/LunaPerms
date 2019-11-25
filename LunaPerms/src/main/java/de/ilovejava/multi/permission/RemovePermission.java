package de.ilovejava.multi.permission;

import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class RemovePermission extends Query{
	public RemovePermission(String Permission, String group) {
		for(Client_Stock c : Utils.getClients()) {
			this.UpdateRemovePermission(c.client(), group, Permission);
		}
	}
}
