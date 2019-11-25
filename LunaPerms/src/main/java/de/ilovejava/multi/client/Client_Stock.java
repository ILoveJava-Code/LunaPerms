package de.ilovejava.multi.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import de.ilovejava.chatcolor.SendChatColor;
import de.ilovejava.multi.querys.Query;
import de.ilovejava.sendpermission.SendPermission;
import de.ilovejava.sendprefix.SendPrefix;
import de.ilovejava.sendtablist.TabList;
import de.ilovejava.sendtagid.TagID;
import de.ilovejava.user.SendUser;
import de.ilovejava.utils.Utils;

public class Client_Stock extends Query implements Client{
	
	private String name;
	private String password;
	private String ip;
	private Socket client;
	
	public Client_Stock(String Password, String User, Socket client) {
		this.password = Password;
		this.name = User;
		this.client = client;
		this.ip = client.getInetAddress().getHostAddress();
		try {this.Login();}catch(Exception e) {this.QueryError(this.name, this.getClass().getSimpleName());}
	}

	@Override
	public String Name() {
		return this.name;
	}

	@Override
	public String Password() {
		return this.password;
	}

	@Override
	public String IP() {
		return  this.ip;
	}

	@Override
	public Socket client() {
		return this.client;
	}

	@Override
	public String getQuery() throws IOException {
		String qry = null;
		DataInputStream in = new DataInputStream(client.getInputStream());
		qry = in.readUTF();
		return qry;
	}

	@Override
	public void sendQuery(String Query) {
		try {
			DataOutputStream out = new DataOutputStream(this.client.getOutputStream());
			out.writeUTF(Query);
		} catch (Exception e) {e.getStackTrace();}
	}

	@Override
	public void Login() throws IOException {
		if(Utils.getMulitUser().containsKey(this.name)) {
			String password = Utils.getMulitUser().get(this.name);
			if(password.equals(this.password)) {
				this.LoginReady(this.client);
				this.StartQueryFinder();
				this.MultiClientConnect(this.name, this.ip);
				Utils.getClients().add(this);
			}else {this.PasswordDeny(this.client); this.client.close();}
		}else {this.UserDeny(this.client); this.client.close();}
	}

	@Override
	public void StartQueryFinder() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				try {
					String[] qry = getQuery().split(">");
					if(qry[0].equalsIgnoreCase("INSERT")) {
						if(qry[1].equalsIgnoreCase("ALL")) {
							if(qry[2].equalsIgnoreCase("GROUPS")) {
								new SendGroups(client);
							}else if(qry[2].equalsIgnoreCase("PERMISSIONS")) {
								new SendPermission(client);
							}else if(qry[2].equalsIgnoreCase("PREFIXS")) {
								new SendPrefix(client);
							}else if(qry[2].equalsIgnoreCase("TABLISTS")) {
								new TabList(client);
							}else if(qry[2].equalsIgnoreCase("TAGIDS")) {
								new TagID(client);
							}else if(qry[2].equalsIgnoreCase("USER")) {
								new SendUser(client);
							}else if(qry[2].equalsIgnoreCase("CHATCOLOR")) {
								new SendChatColor(client);
							}
						}
					}
				}catch(Exception e) {QueryError(name, Client_Stock.class.getSimpleName()); this.cancel();}
			}
		}, 0, 100);
	}
}
