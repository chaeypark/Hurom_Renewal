package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con; // 데이터베이스와 연결을 담당
	public Statement stmt; // 인파라미터 없는 정적 쿼리문을 담당
	public PreparedStatement psmt; // 인파라미터가 있는 동적 쿼리문을 담당
	public ResultSet rs; //SELECT 쿼리문의 결과를 저장
	
	public JDBConnect(ServletContext application) {
		try {
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			
			String url = application.getInitParameter("OracleUrl");
			String id = "test1";
			String pw = "1234";
			
			//String id = application.getInitParameter("OracleId");
			//String pw = application.getInitParameter("OraclePw");
			
			con = DriverManager.getConnection(url, id, pw);
			
			System.out.println("DB 연결에 성공!!(생성자 이용)");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt !=null) stmt.close();
			if(psmt !=null) psmt.close();
			if(con != null) con.close();
			
			System.out.println("JDBC 자원 해제");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
