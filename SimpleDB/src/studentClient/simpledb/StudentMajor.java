package studentClient.simpledb;

import java.sql.*;
import java.util.Properties;

import simpledb.remote.SimpleDriver;

public class StudentMajor {
    public static void main(String[] args) {
		Connection conn = null;
		Properties prop=new Properties();
		try {
			// Step 1: connect to database server
			Driver d = new SimpleDriver();
			System.out.println("test");
			prop.setProperty("UserID","180201045");
			conn = d.connect("jdbc:simpledb://localhost/test", prop);
			System.out.println("test2");

			// Step 2: execute the query
			Statement stmt = conn.createStatement();
			System.out.println("CGD2--> "+stmt.toString());
			String qry = "select SName, DName "
			           + "from DEPT, STUDENT "
			           + "where MajorId = DId";
			ResultSet rs = stmt.executeQuery(qry);

			// Step 3: loop through the result set
			System.out.println("Name\tMajor");
			while (rs.next()) {
				String sname = rs.getString("SName");
				String dname = rs.getString("DName");
				System.out.println(sname + "\t" + dname);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			// Step 4: close the connection
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
