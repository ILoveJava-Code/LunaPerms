package de.ilovejava.multi.permission;

import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.multi.querys.Query;
import de.ilovejava.utils.Utils;

public class UpdateChatColor extends Query{
	public UpdateChatColor(String color, String group) {
		for(Client_Stock c : Utils.getClients()) {
			this.sendChatColor(color, group, c.client());
		}
	}
}
