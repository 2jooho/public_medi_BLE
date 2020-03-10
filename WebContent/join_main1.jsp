<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="blu_android_jsp.ConnectDB_join_main1" %>
    
    <% 
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    String name = request.getParameter("name");
    String sex = request.getParameter("sex");
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    String telecom = request.getParameter("telecom");
    String phone = request.getParameter("phone");
    
    ConnectDB_join_main1 connectDB_join_main1 = ConnectDB_join_main1.getInstance();
    
    String returns = connectDB_join_main1.joindb(id,password,name,sex,year,month, day, telecom, phone);
    out.print(returns);