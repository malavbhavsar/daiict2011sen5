<%@ page import="java.util.*" %>
<%@ page import="com.zimbra.ui.*" %>

<jsp:useBean id="loginFormHandler" class="com.zimbra.ui.LoginFormBean" scope="request">
<jsp:setProperty name="loginFormHandler" property="*"/>
</jsp:useBean>
<% 
   if (loginFormHandler.validate()) {
%>
  	<jsp:forward page="settings.jsp"/><%
   }  else {
%>
    <jsp:forward page="loginretry.jsp"/>
<%
   }
%>