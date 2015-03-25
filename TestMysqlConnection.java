import java.sql.*;

public class TestMysqlConnection {
	
	
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
				
		
		
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost/mydata?" +
		                                   "user=root&password=129114");
		    
		    // Do something with the Connection
		    stmt = conn.createStatement();
		    rs = stmt.executeQuery("select * from emp");
		    while(rs.next()){
		    	System.out.println(rs.getString("deptno"));
		    }
		} catch(ClassNotFoundException e){
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}finally{
			try{
				if(rs != null){
					rs.close();
					rs = null;
				}
				if(stmt != null){
					stmt.close();
					stmt = null;
				}
				if(conn != null){
					conn.close();
					conn = null;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	
}
