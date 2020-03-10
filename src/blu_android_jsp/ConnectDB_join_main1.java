package blu_android_jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB_join_main1 {

	private static ConnectDB_join_main1 instance = new ConnectDB_join_main1();
	
	public static ConnectDB_join_main1 getInstance() {
		return instance;
	}

	public ConnectDB_join_main1() {
	}
	
	static String jdbcUrl = "jdbc:mysql://localhost:3306/DB이름?serverTimezone=UTC";// MySQL 계정 "jdbc:mysql://localhost:3306/DB이름"
	private String dbId = ""; // MySQL 계정 "로컬일 경우 root"
	private String dbPw = ""; // 비밀번호 "mysql 설치 시 설정한 비밀번호"
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private ResultSet rs = null;
	private String sql ;
	private String sql2;
	String returns = null;
	String returns2 = null;
	
	public String joindb(String id, String password, String name, String sex, String year, String month, String day, String telecom, String phone) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			
			sql = "select * from User where phone=? and telecom=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, telecom);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("phone").equals(phone)&&rs.getString("telecom").equals(telecom)) { // 이미 아이디가 있는 경우
					returns = "have";
				} 
			} else { // 입력한 아이디가 없는 경우
				sql2 = "insert into User values(?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, id);
				pstmt2.setString(2, password);
				pstmt2.setString(3, name);
				pstmt2.setString(4, sex);
				pstmt2.setString(5, year);
				pstmt2.setString(6, month);
				pstmt2.setString(7, day);
				pstmt2.setString(8, telecom);
				pstmt2.setString(9, phone);
				pstmt2.executeUpdate();
				
				returns = "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (pstmt2 != null)try {pstmt2.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}
	
}
