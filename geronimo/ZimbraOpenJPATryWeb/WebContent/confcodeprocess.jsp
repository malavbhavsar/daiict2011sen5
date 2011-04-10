<%@ page import="java.util.*" %>
<%@ page import="com.zimbra.ui.*" %>

<jsp:useBean id="confCodeFormHandler" class="com.zimbra.ui.ConfCodeBean" scope="request">
<jsp:setProperty name="confCodeFormHandler" property="*"/>
</jsp:useBean>
<jsp:forward page="/CheckConfCode"/>


