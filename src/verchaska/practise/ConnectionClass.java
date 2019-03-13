package verchaska.practise;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
	public Connection getConnection() {
		Connection con=null;
		try {
			String url="jdbc:mysql://localhost:3306/practise?autoReconnect=true&useSSL=false";
			String user="root";
			 String password="iamrockstarjd";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, user, password);
		}
			catch(Exception e)
		{
				e.printStackTrace();
		}
		return con;

	}

}
