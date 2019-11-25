package de.ilovejava.events;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Event_Leave implements Listener {
	@EventHandler
	public void onLeave(PlayerDisconnectEvent e) {
		//new Remove(e.getPlayer());
	}
}
