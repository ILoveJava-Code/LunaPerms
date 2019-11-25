package de.ilovejava.unset;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Remove {
	public Remove(ProxiedPlayer p) {
		for(String perm : p.getPermissions()) {
			p.setPermission(perm, false);
		}
	}
}
