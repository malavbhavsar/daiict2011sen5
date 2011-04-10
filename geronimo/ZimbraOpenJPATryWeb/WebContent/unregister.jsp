<html>
<body>
<head>
<script type="text/javascript">
	function validateForm_3()
	{
		var x=document.forms["unregister"]["unregusername"].value;
		var y=document.forms["unregister"]["unregpassword"].value;
		if (x==null || x=="" || y==null || y== "")
  		{
  			alert("Please fill the complete information");
  			return false;
  		}
	}
</script>	
</head>
<form name = "unregister" action="unregisterprocess.jsp" method="post" onSubmit="return validateForm_3()">
<center>
<table cellpadding=4 cellspacing=2 border=0>
<th  colspan=2>
<font size=5>User Registration</font>
<br>
<font size=1><sup>*</sup> Required Fields</font>
</th>
<tr >
<td valign=top> 
<b>Username<sup>*</sup></b> 
</td>
<td>
<input type="text" name="unregusername" value="" size=15 maxlength=80>
</td>
<tr >
<td valign=top>
<b>Password<sup>*</sup></b> 
</td>
<td>
<input type="password" name="unregpassword" size=15 value="" maxlength=40>
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