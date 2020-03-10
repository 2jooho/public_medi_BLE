<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="blu_android_jsp.ConnectDB_login" %>

<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	//String type = request.getParameter("type");//로그인 요청인지 회원가입 요청인지를 구분하여 메서드를 실행하도록
                                               
	//싱글톤 방식으로 자바 클래스를 불러옵니다.
		ConnectDB_login connectDB_login = ConnectDB_login.getInstance();
	// if(type.equals("login")) {
		String returns = connectDB_login.logindb(id, pwd);
		out.print(returns);
	//}  
%>
    
