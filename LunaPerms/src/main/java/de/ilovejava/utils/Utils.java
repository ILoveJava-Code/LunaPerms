package de.ilovejava.utils;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import de.ilovejava.groups.PermGroup;
import de.ilovejava.lunaperms.LunaPerms;
import de.ilovejava.multi.client.Client_Stock;
import de.ilovejava.mysql.MySQL;
import lombok.Getter;
import lombok.Setter;

public class Utils {
	@Getter @Setter
	public static LunaPerms instance;
	@Getter @Setter
	public static MySQL mysql;
	@Getter @Setter
	public static Boolean MySQlConnect;
	@Getter @Setter
	public static HashMap<String, PermGroup>PermissionGroups;
	@Getter @Setter
	public static HashMap<String, PermGroup>UserGroups;
	@Getter @Setter
	public static ServerSocket MulitServer;
	@Getter  @Setter
	public static HashMap<String, String>MulitUser;
	@Getter @Setter
	public static ArrayList<Client_Stock>clients;
}
