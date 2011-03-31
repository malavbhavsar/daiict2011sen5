<jsp:useBean id="formHandler" class="com.zimbra.ui.FormBean" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<form action="process.jsp" method=post>
<center>
<table cellpadding=4 cellspacing=2 border=0>
<th bgcolor="#CCCCFF" colspan=2>
<font size=5>USER REGISTRATION</font>
<br>
<font size=1><sup>*</sup> Required Fields</font>
</th>
<tr bgcolor="#c8d8f8">
<td valign=top> 
<b>Username<sup>*</sup></b> 
<br>
<input type="text" name="userName" value="<%=formHandler.getUserName()%>" size=15 maxlength=80>
<br><font size=2 color=red><%=formHandler.getErrorMsg("userName")%></font>
</td>

<td valign=top> 
<b>Mobile No.<sup>*</sup></b> 
<br>
+91
<input type="text" name="mobileNo" value="<%=formHandler.getMobileNo()%>" size=10 maxlength=13>
<br><font size=2 color=red><%=formHandler.getErrorMsg("mobileNo")%></font>
</td>
</tr>
<tr bgcolor="#c8d8f8">
<td valign=top>
<b>Password<sup>*</sup></b> 
<br>
<input type="password" name="password1" size=10 value="<%=formHandler.getPassword1()%>" maxlength=40>
<br>
<font size=2 color=red><%=formHandler.getErrorMsg("password1")%></font>
</td>
<td  valign=top>
<b>Confirm Password<sup>*</sup></b>
<br>
<input type="password" name="password2" size=10 value="<%=formHandler.getPassword2()%>" maxlength=40>
<br>
<font size=2 color=red><%=formHandler.getErrorMsg("password2")%></font>
</td>
</tr>
<tr bgcolor="#c8d8f8">
<td  align=center colspan=2>
<input type="submit" value="Submit"> <input type="reset" value="Reset">
</td>
</tr>
</table>
</center>
</form>
</body>
</html>
