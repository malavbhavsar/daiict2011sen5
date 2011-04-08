
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login Page</title>
<style type="text/css">
<!--
body {
	background-color: #efe7d4;
}
.style7 {font-size: xx-large; }
.style11 {font-size: 14px}
.style13 {font-family: Arial, Helvetica, sans-serif}
-->
</style>
<script type="text/javascript">
function validateForm()
{
var x=document.forms["Main"]["Input"].value
var y=document.forms["Main"]["Input2"].value
if (x==null || x=="" || y==null || y== "")
  {
  alert("Please fill the complete information");
  return false;
  }
}
</script>
</head>

<body>
<p class="style7">&nbsp;</p>
<div align="center">
    <blockquote>&nbsp; </blockquote>
</div>
<blockquote>
<table border="1" align="center" bgcolor="#d7cfbe">
	<tr>
		<td>
			<p align="center"><span class="style7"><img src="mnc.jpg" alt="mnc"/></span></p>
      		<div align="center">
      			<form name = "Main" action="loginprocess.jsp" method="post" onSubmit="return validateForm()">
        			<table border="0" align="center" bordercolor="#333333" bgcolor="#F5F4E0">
              			<tr>	
              				<th valign="middle" colspan="2" align="center" bordercolor="#333333" bgcolor="#F5F4E0">
              					<p align="center" class="style13">Sign in with your Zimbra account </p>
              				</th>
              			</tr>
              			<tr>
              				<td>              		
                				<p align="center" class="style13">
                					Username:</p>
                			</td>
                			<td>
                				<input name="userName" size="32" />
                			</td>
                		</tr>
                		<tr>
                			<td>
                				<p align="center" class="style13">Password:</p>
                			</td>
                			<td>
                				<input name="passWord" type="password" size="32" />
                			</td>
                		</tr>
<!--p align="center"><a href="settings.jsp"-->
<!--button = "return validateForm()">Sign In</button-->
						<tr>
							<td colspan ="2" align="center">
				  				<input type="submit" value="Sign In" align="middle"/>
				  			</td>
				  		</tr>
				  	</table>
			    </form>
			</div>
		</td>
	</tr>
    <tr>
		<td valign="middle" bordercolor="#333333" bgcolor="#F5F4E0">
			<p align="center" class="style13">Not a user?</p>
 			<p align="center">
 				<a href="sms.jsp">
                	<button>Register</button>
              	</a>
            </p>
            <p align="center"><a href="pass_change.jsp"> Change Password</a></p>            
			<p align="center"><a href="mobile_change.jsp"> Change Mobile No.</a></p>
		</td>
	</tr>
</table>
</blockquote>
  <p align="center">&nbsp;</p>
  <div align="center">
    <table width="106%" border="0" bordercolor="#336633" bgcolor="#D7CFBE">
      <tr>
      <td width="27%"><div align="center"><span class="style11"><a href="about_us.jsp">About us</a> </span></div></td>
	  <td width="26%"><div align="center"><a href="help.pdf" class="style11">Help</a></div></td>
      <td width="27%"><div align="center"><a href="faq.jsp" class="style11">FAQ's</a></div></td>
    </tr>
    </table>
  </div>
<p align="center">&nbsp;</p>
</body>
</html>
