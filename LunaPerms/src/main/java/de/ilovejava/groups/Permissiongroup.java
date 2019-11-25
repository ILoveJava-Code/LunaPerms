package de.ilovejava.groups;

import java.util.ArrayList;

public interface Permissiongroup {
	public int DBID();
	public String Name();
	public String Prefix();
	public String Tablist();
	public Integer tabid();
	public ArrayList<String> permission();
	public ArrayList<String> userInGroup();
	public ArrayList<Integer> addedGroup();
	public String messageColor();
	
	public void load();
	public void create();
	public void save();
	public void delete();
	
	public boolean isinGroup(String uuid);
	public boolean hasPermission(String permission);
	
	public boolean isConnectioWithGroup(Integer id);
	public boolean connectWithGroup(Integer id);
	public boolean unconnectWithGroup(Integer id);
	
	public void addPermission(String Permission);
	public void removePermission(String Permission);
	
	public void setDBID(Integer i);
	public void setName(String s);
	public void setPrefix(String s);
	public void setTabList(Integer i);
	public void setTabString(String s);
	public void setPermission(String permission);
	public void setUnserInGroup(String uuid);
	public void addUserToGroup(String uuid,ArrayList<String> oldperms);
	public void removeUserFormGroup(String uuid);
	public void setMessageColor(String color);
}
