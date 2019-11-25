package de.ilovejava.multi.permission;

import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class UpdatePrefix extends Query{
	public UpdatePrefix(String Prefix, String group) {
		for(Client_Stock c : Utils.getClients()) {
			this.updateGroupPrefix(Prefix, group, c.client());
		}
	}
}
