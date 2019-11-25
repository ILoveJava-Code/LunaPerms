package de.ilovejava.multi.querys;

import java.io.DataOutputStream;
import java.net.Socket;

import de.ilovejava.log.Log_Value;

public class Query extends Log_Value implements Querys{

	@Override
	public void sendGroups(String Group, Socket Client) {
		sendQuery(Client, "INSERT>GROUP>"+Group);
	}

	@Override
	public void addPermission(String Permission, String Group, Socket Client) {
		sendQuery(Client, "UPDATE>GROUP>PERMISSION>"+Group+">"+Permission);
	}

	@Override
	public void removePermission(String Permission, String Group, Socket Client) {
		sendQuery(Client, "DELETE>"+Group+">PERMISSION>"+Permission);
	}

	@Override
	public void updateGroup(String uuid, String Group, Socket Client) {
		sendQuery(Client, "PLAYER>UPDATE>GROUP>"+uuid+">"+Group);
	}

	@Override
	public void updateGroupPrefix(String Prefix, String Group, Socket Client) {
		sendQuery(Client, "STRING>UPDATE>GROUP>PREFIX>"+Group+">"+Prefix);
	}

	@Override
	public void updateGroupTab(String Tab, String Group, Socket Client) {
		sendQuery(Client, "STRING>UPDATE>GROUP>TABLIST>"+Group+">"+Tab);
	}

	@Override
	public void updateGroupTagID(String ID, String Group, Socket Client) {
		sendQuery(Client, "STRING>UPDATE>GROUP>TAGID>"+Group+">"+ID);
	}

	public void sendUserTOGroup(String user, String group, Socket c) {
		sendQuery(c, "STRING>UPDATE>GROUP>USER>"+group+">"+user);
	}
	
	@Override
	public void sendUserPermission(String Permissions, String UUID, Socket Client) {
		sendQuery(Client, "PLAYER>SET>USER>PERMISSION>"+UUID+">"+Permissions);
	}

	@Override
	public void addUserPermission(String Permission, String UUID, Socket Client) {
		sendQuery(Client, "PLAYER>ADD>USER>PERMISSION>"+UUID+">"+Permission);
	}

	@Override
	public void removeUserPermission(String Permission, String UUID, Socket Client) {
		sendQuery(Client, "PLAYER>REMOVE>USER>PERMISSION>"+UUID+">"+Permission);
	}

	@Override
	public void UserDeny(Socket Client) {
		sendQuery(Client, "FUNCTION>USER>DENY");
	}

	@Override
	public void PasswordDeny(Socket Client) {
		sendQuery(Client, "FUNCTION>PASSWORD>DENY");
	}

	@Override
	public void LoginReady(Socket Client) {
		sendQuery(Client, "FUNCTION>LOGIN>ACCEPT");
	}
	
	private void sendQuery(Socket c, String Query) {
		try {
			DataOutputStream out = new DataOutputStream(c.getOutputStream());
			out.writeUTF(Query);
		} catch (Exception e) {e.getStackTrace();}
	}

	@Override
	public void GroupSendEnd(Socket c) {
		sendQuery(c, "FUNCTION>GROUPSEND>END");
	}

	@Override
	public void PermissionSendEnd(Socket c) {
		sendQuery(c, "FUNCTION>PERMISSIONSEND>END");
	}

	@Override
	public void PrefixSendEnd(Socket c) {
		sendQuery(c, "FUNCTION>PREFIXSEND>END");
	}

	@Override
	public void TablistSendEnd(Socket c) {
		sendQuery(c, "FUNCTION>TABSEND>END");
	}

	@Override
	public void TagIDSendEnd(Socket c) {
		sendQuery(c, "FUNCTION>TAGSEND>END");
	}

	@Override
	public void UserSendEnd(Socket c) {
		sendQuery(c, "FUNCTION>USERSEND>END");
	}

	@Override
	public void UpdateAddPermission(Socket c, String group,String Permission) {
		sendQuery(c, "FUNCTION>UPDATE>PERMISSION>ADD>" + group+">"+Permission );
	}

	@Override
	public void UpdateRemovePermission(Socket c, String group, String permission) {
		sendQuery(c, "FUNCTION>UPDATE>PERMISSION>REMOVE>"+group+">"+permission);
	}

	@Override
	public void sendChatColor(String color, String group, Socket c) {
		sendQuery(c, "STRING>UPDATE>GROUP>COLOR>"+group+">"+color);
	}

	@Override
	public void ColorSendEnd(Socket c) {
		sendQuery(c, "FUNCTION>COLORSEND>END");
	}

	@Override
	public void removePlayerForGroup(Socket c, String uuid, String group) {
		sendQuery(c, "UPDATE>USER>REMOVE>GROUP>"+ uuid+">"+group);
	}

	@Override
	public void updateUserGroup(Socket c, String uuid, String group) {
		sendQuery(c, "UPDATE>USER>ADD>GROUP>"+uuid+">"+group);
	}
}
