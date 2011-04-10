<%@ page import="java.util.*" %>
<%@ page import="com.zimbra.ui.*" %>
<jsp:useBean id="passChangeFormHandler" class="com.zimbra.ui.PassChangeFormBean" scope="request">
<jsp:setProperty name="passChangeFormHandler" property="*"/>
</jsp:useBean>
<% 
   if (passChangeFormHandler.validate()) {
%>
	
  	<jsp:forward page="new_operationsuccess.html"/><%
   }  else {
%>
    <jsp:forward page="new_changepassretry.jsp"/>
<%
   }
%>