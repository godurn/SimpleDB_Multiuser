package simpledb.server;

import simpledb.remote.*;

import java.rmi.RemoteException;
import java.rmi.registry.*;

public class Startup {
   public static void main(String args[]) throws Exception {
      // configure and initialize the database

	  
	  SimpleDB.init("database");


      // create a registry specific for the server on the default port
      
      Registry reg = LocateRegistry.createRegistry(1099);
      

      // and post the server entry in it
      RemoteDriver d = new RemoteDriverImpl();

      
 
      reg.bind("simpledb", d);

      
      System.out.println("database server ready");
   }
}
