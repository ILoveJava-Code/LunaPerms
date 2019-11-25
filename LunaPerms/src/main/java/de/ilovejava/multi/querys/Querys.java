package de.ilovejava.multi.querys;

import java.net.Socket;

public interface Querys {
	public void sendGroups(String Group, Socket Client);
	public void addPermission(String Permission, String Group, Socket Client);
	public void removePermission(String Permission, String Group, Socket Client);
	public void updateGroup(String uuid, String Group, Socket Client);
	public void updateGroupPrefix(String Prefix, String Group, Socket Client);
	public void updateGroupTab(String Tab, String Group, Socket Client);
	public void updateGroupTagID(String ID, String Group, Socket Client);
	public void sendUserPermission(String Permissions, String UUID, Socket Client);
	public void addUserPermission(String Permission, String UUID, Socket Client);
	public void removeUserPermission(String Permission, String UUID, Socket Client);
	public void sendChatColor(String Color, String Group, Socket Client);
	public void UserDeny(Socket Client);
	public void PasswordDeny(Socket Client);
	public void LoginReady(Socket Client);
	public void GroupSendEnd(Socket c);
	public void PermissionSendEnd(Socket c);
	public void PrefixSendEnd(Socket c);
	public void TablistSendEnd(Socket c);
	public void TagIDSendEnd(Socket c);
	public void UserSendEnd(Socket c);
	public void ColorSendEnd(Socket c);
	public void UpdateAddPermission(Socket c, String group,String Permission);
	public void UpdateRemovePermission(Socket c, String group, String permission);
	public void removePlayerForGroup(Socket c, String uuid, String group);
	public void updateUserGroup(Socket c, String uuid, String group);
}
