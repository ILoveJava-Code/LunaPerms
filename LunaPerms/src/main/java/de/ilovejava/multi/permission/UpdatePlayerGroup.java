package de.ilovejava.multi.permission;

import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class UpdatePlayerGroup extends Query{
	public UpdatePlayerGroup(String uuid, String group) {
		for(Client_Stock c : Utils.getClients()) {
			removePlayerForGroup(c.client(), uuid, group);
		}
	}
}
