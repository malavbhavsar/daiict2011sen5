<jsp:useBean id="passChangeFormHandler" class="com.zimbra.ui.PassChangeFormBean" scope="request"/>
<html>
<body>
<form action="pass_change_process.jsp" method=post>
<center>
<table cellpadding=4 cellspacing=2 border=0>
<th colspan=2>
<font size=5>Change Password</font>
<br>
<font size=1><sup>*</sup> Required Fields</font>
</th>
<tr>
<td valign=top> 
Username<sup>*</sup>
</td>
<td valign=top> 
<input type="text" name="userName" value="" size=15 maxlength=80>
<br>
<%=passChangeFormHandler.getErrorMsg("userName")%>
</td>
</tr>
<tr>
<td valign=top> 
Old Password<sup>*</sup> 
</td>
<td valign=top> 
<input type="password" name="oldPassword" value="" size=15 maxlength=80>
<br>
<%=passChangeFormHandler.getErrorMsg("oldPassword")%>
</td>
</tr>
<tr>
<td valign=top> 
New Password<sup>*</sup> 
</td>
<td valign=top> 
<input type="password" name="newPassword1" value="" size=15 maxlength=80>
<br>
<%=passChangeFormHandler.getErrorMsg("newPassword1")%>
</td>
</tr>
<tr>
<td valign=top> 
Confirm New Password<sup>*</sup> 
</td>
<td valign=top> 
<input type="password" name="newPassword2" value="" size=15 maxlength=80>
<br>
<%=passChangeFormHandler.getErrorMsg("newPassword2")%>
</td>
</tr>
<tr>
<td  align=center colspan=2>
<input type="submit" value="Submit"> <input type="reset" value="Reset">
</td>
</tr>
</table>
</center>
</form>
</body>
</html>