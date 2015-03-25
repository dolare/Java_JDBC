import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArticleTree {
	
	public static void main(String[] args){
		
		new ArticleTree().show();
	}
	
	public void show(){
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn =  DriverManager.getConnection("jdbc:mysql://localhost/bbs?user=root&password=129114");
			String sql0 = "select * from article where pid = 0";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql0);
			while(rs.next()){
				System.out.println(rs.getString("cont"));
				tree(conn,rs.getInt("id"),1); 
			}
		
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e1){
			e1.printStackTrace();
		}
		finally{
			try{
				if(conn!=null){
					conn.close();
					conn = null;
				}
				if(rs!=null){
					rs.close();
					rs = null;
				}
				if(stat!=null){
					stat.close();
					stat = null;
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
	//ตÝน้
	public void tree(Connection conn,int id,int level){
		Statement stat = null;
		ResultSet rs = null;
		StringBuffer strPre = new StringBuffer("");
		for(int i = 0; i < level ; i++){
			strPre.append("	");
		}
		
		try{
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery("select * from article where pid = " + id);
			
			while(rs.next()){
				System.out.println(strPre + rs.getString("cont"));
				if(rs.getInt("isleaf")!=0)
					tree(conn,rs.getInt("id"),level+1);	
			}
			strPre= new StringBuffer("");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
					rs = null;
				}
				if(stat!=null){
					stat.close();
					stat = null;
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		
	}
}
