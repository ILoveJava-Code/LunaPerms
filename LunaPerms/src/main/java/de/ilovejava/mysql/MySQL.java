package de.ilovejava.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import de.ilovejava.log.Log_Value;
import de.ilovejava.utils.Utils;


public class MySQL extends Log_Value{
	 private String HOST = "";
     private String DATABASE = "";
     private String USER = "";
     private String PASSWORD = "";
     private Integer PORT = 3306;
    
     private Connection con;
    
     public MySQL(String host, String database, String user, String password, Integer port) {
             this.HOST = host;
             this.DATABASE = database;
             this.USER = user;
             this.PASSWORD = password;
             this.PORT = port;
             connect();
     }
     
	public void connect() {
             try {
                     con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":"+PORT+"/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
                     this.MySQLConTrue();
                     Utils.setMySQlConnect(true);
             } catch (SQLException e) {
            	 this.MySQLConFalse();
            	 Utils.setMySQlConnect(false);
             }
     }
    
     public void updateMysql() {
    	 Timer t = new Timer();
    	 t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(isConnect()) {
					connect();
				}
			}
		}, 0, 60);
     }
     
     public void close() {
             try {
                     if(con != null) {
                             con.close();
                             System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
                     }
             } catch (SQLException e) {
                     System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
             }
     }
    
     public void update(String qry) {
             try {
                     Statement st = con.createStatement();
                     st.executeUpdate(qry);
                     st.close();
             } catch (SQLException e) {
                     connect();
                     System.err.println(e);
             }
     }
    
     public ResultSet query(String qry) {
             ResultSet rs = null;
            
             try {
                     Statement st = con.createStatement();
                     rs = st.executeQuery(qry);
             } catch (SQLException e) {
                     connect();
                     System.err.println(e);
             }
             return rs;
     }
     
     public boolean isConnect() {
    	 try {
			return con.isClosed();
		} catch (SQLException e) {}
     	return false;
     }
}
