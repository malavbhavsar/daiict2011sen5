<jsp:useBean id="formHandler" class="com.zimbra.ui.FormBean" scope="request"/>
<html>
<body>
<center>
<table cellpadding=4 cellspacing=2 border=0>
<th bgcolor="#CCCCFF" colspan=2>
<font size=5>USER REGISTRATION SUCCESSFUL!</font>
</th>
<tr bgcolor="#c8d8f8">
<td valign=top>
<b>Username</b>
<br>
<jsp:getProperty name="formHandler" property="userName"/>
</td>
<td valign=top> 
<b>Mobile No.</b>
<br>
+91
<jsp:getProperty name="formHandler" property="mobileNo"/>
</td> 
</tr>
</table>
</center>
</body>
</html>