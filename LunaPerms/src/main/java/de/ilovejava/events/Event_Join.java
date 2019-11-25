package de.ilovejava.events;

import de.ilovejava.add.Add;
import de.ilovejava.defaultcreate.DefaultCreate;
import de.ilovejava.uuid.uuidfetcher;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Event_Join implements Listener {
	@EventHandler
	public void onJoin(PostLoginEvent e) {
		String uuid = uuidfetcher.getUUID(e.getPlayer().getName()).toString();
		new DefaultCreate(uuid);
		new Add(uuid, e.getPlayer());
	}
}
