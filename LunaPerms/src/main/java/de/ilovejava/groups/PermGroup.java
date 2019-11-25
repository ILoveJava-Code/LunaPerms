package de.ilovejava.groups;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import de.ilovejava.multi.permission.AddPermission;
import de.ilovejava.multi.permission.RemovePermission;
import de.ilovejava.multi.permission.UpdateChatColor;
import de.ilovejava.multi.permission.UpdatePlayerGroup;
import de.ilovejava.multi.permission.UpdatePrefix;
import de.ilovejava.multi.permission.UpdateUserGroup;
import de.ilovejava.utils.Utils;
import de.ilovejava.uuid.uuidfetcher;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;



public class PermGroup implements Permissiongroup{

	private Integer db;
	private String gname;
	private String prefix;
	private String tagprefix;
	private Integer tagid;
	private ArrayList<String> perms;
	private ArrayList<String> user;
	private ArrayList<Integer> impGroups; 
	private String MessageColor;
	
	public PermGroup(Integer id) {this.setDBID(id); this.load();}
	public PermGroup(String Name) {this.setName(Name);}
	
	@Override
	public int DBID() {
		return this.db;
	}

	@Override
	public String Name() {
		return this.gname;
	}

	@Override
	public String Prefix() {
		return this.prefix;
	}

	@Override
	public String Tablist() {
		return this.tagprefix;
	}

	@Override
	public Integer tabid() {
		return this.tagid;
	}

	@Override
	public ArrayList<String> permission() {
		return this.perms;
	}

	@Override
	public ArrayList<String> userInGroup() {
		return this.user;
	}

	@Override
	public ArrayList<Integer> addedGroup() {
		return this.impGroups;
	}

	@Override
	public void load() {
		ResultSet rs = Utils.getMysql().query("SELECT * FROM LunaPerms_Group WHERE id='"+this.DBID()+"'");
		try {
			if(rs.next()) {
				this.setName(rs.getString("Name"));
				this.setPrefix(rs.getString("Prefix"));
				this.setTabString(rs.getString("Tablist"));
				this.setTabList(rs.getInt("TagID"));
				this.setMessageColor(rs.getString("MessageColor"));
			}
			rs.close();
		}catch(Exception e) {e.printStackTrace();}
		this.perms = new ArrayList<>();
		this.user = new ArrayList<>();
		this.impGroups = new ArrayList<>();
		Utils.getPermissionGroups().put(this.Name(), this);
	}

	@Override
	public void create() {
		Utils.getMysql().update("INSERT INTO LunaPerms_Group(Name,Prefix,Tablist,TagID,MessageColor) VALUES('"+this.Name()+"','null','null','0','&f')");
		this.getLastRowID();
		this.load();
		
	}

	private void getLastRowID() {
		ResultSet rs = Utils.getMysql().query("SELECT id FROM LunaPerms_Group ORDER BY id DESC LIMIT 1");
		try {
			if(rs.next()) {
				this.setDBID(rs.getInt("id"));
			}
			rs.close();
		}catch(Exception e) {e.printStackTrace();}
		System.out.println(this.DBID());
	}
	
	@Override
	public void save() {
		Utils.getMysql().update("UPDATE LunaPerms_Group SET Name='"+this.Name()+"', Prefix='"+this.prefix+"',Tablist='"+this.Tablist()+"',TagID='"+this.tabid()+"',MessageColor='"+this.MessageColor+"' WHERE id='"+this.DBID()+"'");
	}

	@Override
	public void delete() {
		Utils.getMysql().update("DELETE FROM LunaPerms_Group WHERE id='"+this.DBID()+"'");
	}

	@Override
	public boolean isinGroup(String uuid) {
		return this.user.contains(uuid);
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.perms.contains(permission);
	}

	@Override
	public boolean isConnectioWithGroup(Integer id) {
		return this.impGroups.contains(id);
	}

	@Override
	public boolean connectWithGroup(Integer id) {
		this.impGroups.add(id);
		return true;
	}

	@Override
	public boolean unconnectWithGroup(Integer id) {
		this.impGroups.remove(id);
		return true;
	}

	@Override
	public void addPermission(String Permission) {
		this.perms.add(Permission);
		Utils.getMysql().update("INSERT INTO LunaPerms_Permission(GroupID,Permission,PermGroup) VALUES('"+this.DBID()+"','"+Permission+"','"+this.Name()+"')");
		addNewPermsToUser(Permission);
		new AddPermission(Permission, this.Name());
	}

	@Override
	public void removePermission(String Permission) {
		this.perms.remove(Permission);
		Utils.getMysql().update("DELETE FROM LunaPerms_Permission WHERE Permission='"+Permission+"' ORDER BY GroupID='"+this.DBID()+"'");
		removePermsToUser(Permission);
		new RemovePermission(Permission, this.Name());
	}

	@Override
	public void setDBID(Integer i) {
		this.db = i;
	}

	@Override
	public void setName(String s) {
		this.gname = s;
	}

	@Override
	public void setPrefix(String s) {
		this.prefix = s;
		new UpdatePrefix(s, this.Name());
	}

	@Override
	public void setTabList(Integer i) {
		this.tagid = i;
	}

	@Override
	public void setTabString(String s) {
		this.tagprefix = s;
	}

	@Override
	public void setPermission(String permission) {
		this.perms.add(permission);
	}

	@Override
	public void setUnserInGroup(String uuid) {
		this.user.add(uuid);
	}

	@Override
	public void addUserToGroup(String uuid, ArrayList<String> oldperms) {
		this.user.add(uuid);
		Utils.getMysql().update("UPDATE LunaPerms_User SET PermGroup='"+this.Name()+"' WHERE UUID='"+uuid+"'");
		try {removeOldPerms(uuid,oldperms);}catch(Exception e) {e.printStackTrace();}
		addNewPermission(uuid);
		Utils.getUserGroups().put(uuid, this);
		new  UpdateUserGroup(this.Name(), uuid);
	}

	@Override
	public void removeUserFormGroup(String uuid) {
		this.user.remove(uuid);
		Utils.getMysql().update("UPDATE LunaPerms_User SET PermGroup='default' WHERE UUID='"+uuid+"'");
		setDefault(uuid);
	}
	
	private void setDefault(String uuid){
		Utils.getPermissionGroups().get("default").addUserToGroup(uuid,this.perms);
		new UpdatePlayerGroup(uuid, this.Name());
	}
	
	public void setPlayerToGroup(String uuid) {
		this.user.add(uuid);
	}

	private void removeOldPerms(String uuid, ArrayList<String>permission) {
		if(BungeeCord.getInstance().getPlayer(uuidfetcher.getName(UUID.fromString(uuid))).isConnected()) {
			ProxiedPlayer p = BungeeCord.getInstance().getPlayer(uuidfetcher.getName(UUID.fromString(uuid)));
			System.out.println(p.getPermissions().size());
			for(String Permission : permission) {
				p.setPermission(Permission, false);
			}
		}
	}
	
	private void addNewPermission(String uuid) {
		if(BungeeCord.getInstance().getPlayer(uuidfetcher.getName(UUID.fromString(uuid))).isConnected()) {
			ProxiedPlayer p = BungeeCord.getInstance().getPlayer(uuidfetcher.getName(UUID.fromString(uuid)));
			for(String Permission : this.perms) {
				p.setPermission(Permission, true);
			}
		}
	}
	
	private void addNewPermsToUser(String perm) {
		for(String uuid : user) {
			if(BungeeCord.getInstance().getPlayer(uuidfetcher.getName(UUID.fromString(uuid))).isConnected()) {
				BungeeCord.getInstance().getPlayer(uuidfetcher.getName(UUID.fromString(uuid))).setPermission(perm, true);
			}
		}
	}
	
	private void removePermsToUser(String perm) {
		for(String uuid : user) {
			if(BungeeCord.getInstance().getPlayer(uuidfetcher.getName(UUID.fromString(uuid))).isConnected()) {
				BungeeCord.getInstance().getPlayer(uuidfetcher.getName(UUID.fromString(uuid))).setPermission(perm, false);
			}
		}
	}
	@Override
	public String messageColor() {
		return this.MessageColor;
	}
	@Override
	public void setMessageColor(String color) {
		this.MessageColor = color;
		new UpdateChatColor(color, this.Name());
	}
	
}
