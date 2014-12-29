package studentClient.simpledb;

import java.util.Properties;

import simpledb.tx.Transaction;
import simpledb.query.*;
import simpledb.server.SimpleDB;

/* This is a version of the StudentMajor program that
 * accesses the SimpleDB classes directly (instead of
 * connecting to it as a JDBC client).  You can run it
 * without having the server also run.
 * 
 * These kind of programs are useful for debugging
 * your changes to the SimpleDB source code.
 */

public class StudentMajorNoServer {
	public static void main(String[] args) {
		try {
			// analogous to the driver
			SimpleDB.init("studentdb");
			// analogous to the connection
			Properties prop=new Properties();
			prop.setProperty("UserID","180201046");
			Transaction tx = new Transaction();
			System.out.println("deneme1");
			// analogous to the statement
			String qry = "select SName, DName "
		        + "from DEPT, STUDENT "
		        + "where MajorId = DId";	
			Plan p = SimpleDB.planner().createQueryPlan(qry, tx,prop);
			System.out.println("deneme2");
			// analogous to the result set
			Scan s = p.open();
			System.out.println("Name\tMajor");
			while (s.next()) {
				String sname = s.getString("sname"); //SimpleDB stores field names
				String dname = s.getString("dname"); //in lower case
				System.out.println(sname + "\t" + dname);
			}
			s.close();
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
