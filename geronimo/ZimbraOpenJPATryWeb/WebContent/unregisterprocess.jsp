<%@ page import="java.util.*" %>
<%@ page import="com.zimbra.ui.*" %>

<jsp:useBean id="unregFormHandler" class="com.zimbra.ui.UnregFormBean" scope="request">
<jsp:setProperty name="unregFormHandler" property="*"/>
</jsp:useBean>
<% 
   if (unregFormHandler.validate()) {
%>
  	<jsp:forward page="new_operationsuccess.html"/><%
   }  else {
%>
    <jsp:forward page="unregisterretry.jsp"/>
<%
   }
%>