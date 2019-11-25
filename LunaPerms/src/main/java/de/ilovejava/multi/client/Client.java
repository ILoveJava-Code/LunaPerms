package de.ilovejava.multi.client;

import java.io.IOException;
import java.net.Socket;

public interface Client {
	public String Name();
	public String Password();
	public String IP();
	public Socket client();
	
	public String getQuery() throws IOException;
	public void sendQuery(String Query);
	public void Login() throws IOException;
	public void StartQueryFinder();
}
