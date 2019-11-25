package de.ilovejava.log;

public interface Log {
	public void SaveTypeNotVali();
	public void MySQLConTrue();
	public void MySQLConFalse();
	public void DisablePlugin();
	public void ConnectionWrong();
	public void InsertTables();
	public void LoadGroup(String name);
	public void LoadGroupPerm(Integer loads);
	public void UserGroupLoad(Integer loads);
	public void MultiClientConnect(String Name, String ip);
	public void QueryError(String User, String Class);
	public void MultiUserLoad(Integer i);
}
