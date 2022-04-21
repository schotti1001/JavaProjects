
package schwebebahn;

import java.sql.*;
 
public class JDBCVerbindung { 
	
	static Connection verbindung = null;
	static Connection verbindungAccess = null;
	static Connection verbindungMySql = null;
	static String db = "MySql";
		
	public static Connection getVerbindung() throws Exception {
		verbindung = null;
		
		if (db.equals("Access")){
			getVerbindungAccess();	
			verbindung = verbindungAccess;
		}
		
		if (db.equals("MySql")){
			getVerbindungMySql();	
			verbindung = verbindungMySql;
		}
		
		return(verbindung);
	}

   
   private static void getVerbindungAccess() throws Exception
   {
    if(verbindungAccess==null)
   	  {
   		try{
	   		Class.forName("org.hsqldb.jdbcDriver");
	   		String datenbank = "jdbc:ucanaccess://H:/daten/JavaDB.accdb";
	   		verbindungAccess=DriverManager.getConnection(datenbank);
   		
   		}catch(Exception e){
   			throw new Exception("Verbindung zur Access-Datenbank konnte nicht hergestellt werden!");
   		}
   	}
  } 
  

   private static void getVerbindungMySql() throws Exception
   {
    if(verbindungMySql==null)
   	  {
   		try{
   			Class.forName("com.mysql.jdbc.Driver");
   			String url = "jdbc:mysql://ux4.edvschule-plattling.de/db_mschotte";
   			verbindungMySql = DriverManager.getConnection(url,"mschotte","mschotte");
  		
   		}catch(Exception e){
   			throw new Exception("Verbindung zur MySql-Datenbank konnte nicht hergestellt werden!", e);
   		}
   	}
  } 
  
  
 public static void schliesseVerbindung() throws Exception
  {
  	try{
  		if(verbindung != null)
  			{
  				verbindung.close();
  				verbindung = null;
  			}
  		
  		if(verbindungAccess != null)
			{
				verbindungAccess.close();
				verbindungAccess = null;
			}
  		  		
  		if(verbindungMySql != null)
		{
			verbindungMySql.close();
			verbindungMySql = null;
		}
  		
  	}catch(Exception e){
		throw new Exception("Verbindung zur " + db + " Datenbank konnte nicht getrennt werden!");
	}
  }    
 
 public static void setDB(String pDb){
 	db = pDb;
 }

public static String getDb() {
	return db;
} 
  
}