package de.ilovejava.multi;

import java.net.ServerSocket;
import java.util.logging.Level;

import de.ilovejava.multi.connectionpaser.ConnectionPaser;
import de.ilovejava.utils.Utils;
import net.md_5.bungee.BungeeCord;

public class Server extends Thread{
	
	Integer port;
	
	public Server(Integer port) {this.port = port;}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		try {
			Utils.setMulitServer(new ServerSocket(port));
			BungeeCord.getInstance().getLogger().log(Level.INFO, "§bThe Multi Server is now on the port:§e "+port+"§b on the move!");
			new ConnectionPaser();
			this.sleep(100);
		}catch(Exception e) {e.printStackTrace();}
	}
}
