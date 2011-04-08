<%@ page import="java.util.*" %>
<%@ page import="com.zimbra.ui.*" %>

<jsp:useBean id="passChangeFormHandler" class="com.zimbra.ui.PassChangeFormBean" scope="request">
<jsp:setProperty name="passChangeFormHandler" property="*"/>
</jsp:useBean>
<% 
   if (passChangeFormHandler.validate()) {
%>
  	<jsp:forward page="pass_change_success.jsp"/><%
   }  else {
%>
    <jsp:forward page="pass_change_retry.jsp"/>
<%
   }
%>