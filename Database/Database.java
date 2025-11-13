/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Quad 7
 */
public class Database {
  static Connection con;
	public static Connection getconnection()
	{
 		
 			
		try
		{
			Class.forName("com.mysql.jdbc.Driver");	
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ddosattack?characterEncoding=utf8","root","Iamnumber1");
		}
		catch(Exception e)
		{
			System.out.println("class error");
		}
		return con;
	}
    
}
