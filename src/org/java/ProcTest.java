package org.java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class ProcTest {

	public static void main(String[] args) throws Exception {
		
	
	Connection conn = null;
	CallableStatement proc = null;
	
	Class.forName("org.postgresql.Driver");

    conn =   DriverManager.getConnection("jdbc:postgresql://10.161.88.16:5432/m1_lsms?currentSchema=m1np","m1_postgres", "m1_postgres");


    System.out.println("Successfully Connected.");
    try {
    	proc = conn.prepareCall("call PKG_PORT$SCN_INSERT(?,?,?,?,?,?,?)");
		proc.setString(1, "6593540016");		// MSISDN
		proc.setString(2, "1412");	// RN
		proc.setString(3, "0");	// PREPAID/POSTPAID
		proc.setString(4, "20211109");	// PORTED DATE
		proc.setString(5, "test");					// UPDATE BY
		proc.setString(6, null);
		proc.setString(7, null);
		proc.registerOutParameter(6, java.sql.Types.VARCHAR);	//Return Code
		proc.registerOutParameter(7, java.sql.Types.VARCHAR);	//Return Message
		proc.execute();
		int retcode = Integer.parseInt(proc.getString(6));
		String msg = proc.getString(7);
		System.out.println("retcode :"+retcode);
		System.out.println("msg : "+msg);
	//conn.setAutoCommit(false);
	//conn.rollback();
//	System.out.println("PKG_LW return port type - " + proc.getString(7));
//	System.out.println("PKG_LW return code - " + proc.getString(6));
	int return_msg = (proc.getInt(6));
	System.out.println(return_msg);
 	ProcTest procTest = new ProcTest();
	if (!proc.getString(6).equals("000")) {
//		if(proc.getString(3).equals("P0002")) {
//			System.out.println("No records founds in db. Please try again");
//		}
		//throw new Exception(procTest.formatErrorString(return_msg));
	}
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public String formatErrorString(String errorStr) {
		int index = errorStr.indexOf(":");
		System.out.println("errorStr is : "+errorStr);
		if (index == -1) {
			if (errorStr.equals("query returned no rows")) {
				return "User account does not exist. Please enter a valid user account.";
			} else {
				return errorStr.substring(index + 2);
			}
		}
		return errorStr;
	}
}
	
