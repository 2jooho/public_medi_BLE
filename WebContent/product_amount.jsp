<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="blu_android_jsp.ConnectDB_product_amount" %>

<%
	JSONObject list=new JSONObject();
	JSONObject obj=new JSONObject();
	JSONArray arr = new JSONArray();
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("product_name");
	//String name = "쌍화";
	//JSONObject returns = new JSONObject();
            String return1,return2;
     
		ConnectDB_product_amount product_amount = ConnectDB_product_amount.getInstance();
		product_amount.result(name);
			return1=product_amount.amount;
			return2=product_amount.origin;
			obj.put("amount", return1);
			obj.put("origin",return2);
			arr.add(obj);
			list.put("list", arr);
			
			
		System.out.println(name);
		System.out.println(return1);
		System.out.println(return2);
		//System.out.println(returns.toJSONString());
		out.print(list.toJSONString());
		
%>
    
