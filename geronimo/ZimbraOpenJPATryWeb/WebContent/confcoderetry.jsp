<jsp:useBean id="confCodeFormHandler" class="com.zimbra.ui.ConfCodeBean" scope="request">
</jsp:useBean>
<html>
<head>
<script type="text/javascript">
	function validateForm_1()
	{
		var x=document.forms["ConfirmForm"]["confcode"].value;
		if (x==null || x=="")
  		{
  			alert("Please enter the confirmation code");
  			return false;
  		}
	}
</script>
</head>
<body>
<form name= "ConfirmForm" form action="confcodeprocess.jsp" method="post" onSubmit="validateForm_1()">
<center>
<table cellpadding=4 cellspacing=2 border=0>
<tr>
<th colspan=2>
<font size=5>Enter Confirmation Code</font>
<br>
<font size=1><sup>*</sup> Required Fields</font>
</th>
</tr>
<tr>
<td valign=top>
<b>Confirmation Code<sup>*</sup></b> 
</td>
</tr>
<tr>
<td>
Username <%=session.getAttribute("user") %>
</td>
</tr>
<tr>
<td>
<input type="text" name="confcode" value="" size=15 maxlength=80>
<%=confCodeFormHandler.getErrorMsg("confcode")%>
</td>
</tr>
<tr>
<td align=center colspan=2>
<input type="submit" value="Submit">
</td>
</tr>
</table>
</center>
</form>
<form action="toresendconfcode.jsp" method=post>
<center>
You can resend confirmation code five times in two hour.
<input type="submit" value="Resend">
</center>
</form>
</body>
</html>