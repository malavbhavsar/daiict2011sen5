<html>
<head>
<script type="text/javascript">
	function validateForm_2()
		{
			var w=document.forms["sms"]["userName"].value;
			var x=document.forms["sms"]["mobileNo"].value;
			var y=document.forms["sms"]["password1"].value;
			var z=document.forms["sms"]["password2"].value;

		if (w==null || w=="" || x==null || x== "" || y==null || y=="" || z==null || z=="")
  		{
  			alert("Please fill the complete information");
  			return false;
  		}
		else if (x!=10)
		{
			alert("Please enter a valid 10-digit mobile number")
		}
		else if (y!=z)
		{
			alert("Password doesn't match. Please check again");
			return false;
		}
	}
</script>
</head>
<body>
<form name = "sms" action="process.jsp" method="post" onSubmit="return validateForm_2()">
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
<input type="text" name="userName" value="" size=15 maxlength=80>
</td>
<tr>
<td valign=top> 
<b>Mobile No.<sup>*</sup></b> 
</td>
<td>
+91
<input type="text" name="mobileNo" value="" size=10 maxlength=10>
</td>
</tr>
<tr >
<td valign=top>
<b>Password<sup>*</sup></b> 
</td>
<td>
<input type="password" name="password1" size=15 value="" maxlength=40>
</td>
</tr>
<tr>
<td  valign=top>
<b>Confirm Password<sup>*</sup></b>
</td>
<td>
<input type="password" name="password2" size=15 value="" maxlength=40>
</td>
</tr>
<tr>
<tr >
<td  align=center colspan=2>
<input type="submit" value="Submit"> <input type="reset" value="Reset">
</td>
</tr>
</table>
</center>
</form>
</body>

</html>