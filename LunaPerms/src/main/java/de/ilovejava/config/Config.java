package de.ilovejava.config;

public interface Config {
	public String SaveType();
	public String MySQLHost();
	public String MySQLUser();
	public String MySQLPassword();
	public String MySQLDataBase();
	public Integer MySQlPort();
	public boolean Multiuse();
	public Integer MultiPort();
	
	public void load();
	public void save();
	public void isSet(String s, Object obj);
	
	public void setSaveType(String s);
	public void setMySQLHost(String s);
	public void setMySQLUser(String s);
	public void setMySQLPassword(String s);
	public void setMySQLDataBase(String s);
	public void setMySQLPort(Integer i);
	public void setMultiuse(Boolean b);
	public void setMultiPort(Integer i);
}
