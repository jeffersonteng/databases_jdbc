package database;
import java.io.*;
import java.sql.*;


public class DataBaseCalls
{	

	//select * from stub_type where ID='1' => return "BID"
	public String getOneStringValue(Connection conn)
	{ 
		ResultSet rs = null;
		Statement stmt = null;
		String ret = null;
		try {
			conn.setAutoCommit(false);
			String sSQL = "SELECT country FROM `symbols` WHERE id =1 LIMIT 0 , 30"; 
			//System.out.println("sSQL="+sSQL); 
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sSQL);
			while (rs.next()) { 
				ret = rs.getString("country");
			}
			stmt.close();
			conn.commit();
		} catch (SQLException ex1) {
		}
		catch (Exception ex2) {
		}
		finally { 
			try {
				if (stmt!=null)
					stmt.close();
			}
			catch (SQLException ex3) {
			}
		}
		return ret;
	}  //end of method 


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataBaseCalls dbCalls = new DataBaseCalls();

		Connection conn=null;;
		try {
			conn = DataBaseConnectionSingleton.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String s = dbCalls.getOneStringValue(conn); 
		System.out.println("s=" + s);

	}

}