package org.java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class QueryDB {
	

		   public static void main( String args[] ) 

		{
			   
		   Connection c = null;


		   try {
			   String dblink_query = "SELECT m1np.dblink_connect('myconn', 'rpt_server')";
				String query = "SELECT * FROM m1np.dblink('myconn','SELECT SERVER_ID, SERVER_NAME, STATUS FROM RPTADM.SERVER_STATUS ORDER BY SERVER_ID') AS t(SERVER_ID varchar,SERVER_NAME varchar,STATUS varchar)";
				
			   //String sql = "SELECT * FROM m1np.dblink('myconn','"+query+"')  AS t(TDR_LOG_TIME varchar,REQUEST_TIME varchar,MSISDN varchar,CAUSE_CODE varchar,REQ_ID varchar,APP_ID varchar,RN varchar,CARD_TYPE varchar,SOURCE_IP varchar)";
				
		      Class.forName("org.postgresql.Driver");

		      c =   DriverManager.getConnection("jdbc:postgresql://10.161.88.16:5432/m1_lsms","m1_postgres", "m1_postgres");


		      System.out.println("Successfully Connected.");
		      System.out.println(query);
		      System.out.println("******************************************");
		      System.out.println(query);
		      CallableStatement callableStatement =
		    		    c.prepareCall(dblink_query);
		      CallableStatement callableStatement1 =
		    		    c.prepareCall(query);

		      callableStatement.execute();
		      callableStatement1.execute();
		      ResultSet rs = callableStatement1.getResultSet();
		      System.out.println("after resultset");
		      while ( rs.next() ) {
		    	  String outServerId = rs.getString("SERVER_ID");
		    	  String outServerName = rs.getString("SERVER_NAME");
		    	  String outStatus = rs.getString("STATUS");
					
		    	  System.out.println("outServerId is : "+outServerId);
		    	  System.out.println("outServerName is : "+outServerName);
		    	  System.out.println("outStatus is : "+outStatus);

		         //int albumid = rs.getInt("AlbumId");
//		    	  String  key = rs.getString("user_name");
//		          String  value = rs.getString("mobile_contact");
//		         String  key = rs.getString("rid");
//		         String  value = rs.getString("reference_id");
//		         String status  = rs.getString("npo_status");
		    	  //String key = rs1.getString("USER_ID");
		    	  //String value = rs.getString("subscribernumber");
		          //System.out.printf( "UserId = %s  ", key);

		         System.out.println();

		      }

		      rs.close();
		      c.close();

		   } catch ( Exception e ) {

		      System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		      e.printStackTrace();
		      System.exit(0);

		   }

		   System.out.println(" Data Retrieved Successfully ..");
		   
		   
		}
}
