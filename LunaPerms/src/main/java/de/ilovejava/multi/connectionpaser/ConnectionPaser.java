package de.ilovejava.multi.connectionpaser;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import de.ilovejava.log.Log_Value;
import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.utils.Utils;

public class ConnectionPaser extends Log_Value{
	public ConnectionPaser() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				try {
					Socket c = Utils.getMulitServer().accept();
					String[] query = getLastSend(c).split(">");
					System.out.println("Connection Found!");
					new Client_Stock(query[2], query[1], c);
				} catch (IOException e) {QueryError("Not Found", ConnectionPaser.class.getSimpleName());}
			}
		}, 0, 100);
	}
	
	public String getLastSend(Socket client) throws IOException {
		String qry = null;
		DataInputStream in = new DataInputStream(client.getInputStream());
		qry = in.readUTF();
		return qry;
	}
	
}
