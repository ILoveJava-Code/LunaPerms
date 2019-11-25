package de.ilovejava.multi.permission;

import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class UpdateUserGroup extends Query{
	public UpdateUserGroup(String group, String uuid) {
		for(Client_Stock cs : Utils.getClients()) {
			this.updateUserGroup(cs.client(), uuid, group);
		}
	}
}
