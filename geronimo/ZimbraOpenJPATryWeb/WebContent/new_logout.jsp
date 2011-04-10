<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%session.removeAttribute("User");
session.invalidate();	
%>
<jsp:forward page="new_login.jsp"/>
    