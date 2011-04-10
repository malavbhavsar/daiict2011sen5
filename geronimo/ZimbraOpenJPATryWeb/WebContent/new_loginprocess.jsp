<%@ page import="java.util.*" %>
<%@ page import="com.zimbra.ui.*" %>
<jsp:useBean id="loginFormHandler" class="com.zimbra.ui.LoginFormBean" scope="request">
<jsp:setProperty name="loginFormHandler" property="*"/>
</jsp:useBean>
<% 
   if (loginFormHandler.validate()) {
   		String name = request.getParameter("myusername");
   		session.setAttribute("username",name);
%>	
  	<jsp:forward page="new_home.jsp"/>
<%
   }  else {
%>
    <jsp:forward page="new_loginretry.jsp"/>
<%
   }
%>