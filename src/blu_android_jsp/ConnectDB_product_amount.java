package blu_android_jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConnectDB_product_amount {

	private static ConnectDB_product_amount instance = new ConnectDB_product_amount();
	
	public static ConnectDB_product_amount getInstance() {
		return instance;
	}
	public ConnectDB_product_amount() {
	}
	static String jdbcUrl = "jdbc:mysql://localhost:3306/DB이름?serverTimezone=UTC";// MySQL 계정 "jdbc:mysql://localhost:3306/DB이름"
	private String dbId = ""; // MySQL 계정 "로컬일 경우 root"
	private String dbPw = ""; // 비밀번호 "mysql 설치 시 설정한 비밀번호"
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql ;
	public String amount,origin;

	public void result(String name) {
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql="select amount, product_origin from BLE_app_info where product_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				amount=rs.getString("amount");
				origin = rs.getString("product_origin");
				//obj.put("amount",rs.getString("amount"));
				//obj.put("origin",rs.getString("product_origin"));
			}
			
			
			//arr.add(obj);
			//list.put("list", arr);
		}catch (Exception e) {

		} finally {
			
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
		

	}
}