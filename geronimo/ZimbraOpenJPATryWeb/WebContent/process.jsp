<%@ page import="java.util.*" %>
<%@ page import="com.zimbra.ui.*" %>

<jsp:useBean id="formHandler" class="com.zimbra.ui.FormBean" scope="request">
<jsp:setProperty name="formHandler" property="*"/>
</jsp:useBean>
<% 
   if (formHandler.validate()) {
%>
  	<jsp:forward page="/DBHandler"/><%
   }  else {
%>
    <jsp:forward page="retry.jsp"/>
<%
   }
%>
