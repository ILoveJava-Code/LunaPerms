package de.ilovejava.events;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.utils.Utils;
import de.ilovejava.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PermissionCheckEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PermissionCheck implements Listener {
	@EventHandler
	public void permissionCheck(PermissionCheckEvent e) {
		if(e.getSender() instanceof ProxiedPlayer) {
			System.out.println("Abfrage wird gestartet!");
			ProxiedPlayer p = (ProxiedPlayer) e.getSender();
			String uuid = uuidfetcher.getUUID(p.getName()).toString();
			PermGroup pg = Utils.getUserGroups().get(uuid);
			char c = e.getPermission().charAt(0);
			if(c == '-' && pg.hasPermission(e.getPermission())) {
				System.out.println("Hat permission 1");
				e.setHasPermission(false);
			}else if(pg.hasPermission("*")) {
				System.out.println("Hat permission *");
				e.setHasPermission(true);
			}else if(pg.hasPermission(e.getPermission())) {
				System.out.println("Hat permission xyz!");
				e.setHasPermission(true);
			}else {e.setHasPermission(false);}
		}
	}
}
