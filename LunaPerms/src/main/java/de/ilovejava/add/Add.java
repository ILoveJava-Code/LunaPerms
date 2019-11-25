package de.ilovejava.add;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Add {
	public Add(String uuid, ProxiedPlayer p) {
		PermGroup pg = Utils.getUserGroups().get(uuid);
		for(String perm : pg.permission()) {
			p.setPermission(perm, true);
		}
	}
}
