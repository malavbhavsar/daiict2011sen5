<jsp:useBean id="loginFormHandler" class="com.zimbra.ui.LoginFormBean" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>MNC</title>

<link rel="stylesheet" href="new_page.css" type="text/css"/>
<script type="text/javascript" src="jquery.min.js"></script>
<script type="text/javascript" src="new_page.js"></script>
<script>
	var activeDone = 'login';
</script>
<script type="text/javascript">
	function validateForm()
	{
		var x=document.forms["Main"]["myusername"].value;
		var y=document.forms["Main"]["mypassword"].value;
		if (x==null || x=="" || y==null || y== "")
  		{
  			alert("Please fill the complete information");
  			return false;
  		}
	}
	function validateForm5()
	{
		var w=document.forms["Main1"]["passChangeUserName"].value;
		var x=document.forms["Main1"]["oldPassword"].value;
		var y=document.forms["Main1"]["newPassword1"].value;
		var z=document.forms["Main1"]["newPassword2"].value;

		if (w==null || w=="" || x==null || x== "" || y==null || y=="" || z==null || z=="")
  		{
  			alert("Please fill the complete information");
  			return false;
  		}
		else if (y!=z)
		{
			alert("New password doesn't match. Please check again");
			return false;
		}
	}
</script>
<style type="text/css">
</style>
</head>
<body>
	<img src="mnc1.jpg" align="top" height="100" width="240" alt="mnc" />
	<div id="done-nav" style="display:inline">
		<ul>			
			<li><label title="login" class="active" id="login-nav">Login</label></li>
			<li><label title="changepassword"  id="changepassword-nav">Change Password</label></li>
			<li><label title="faqs"  id="faqs-nav">FAQ's</label></li>
		</ul>
	</div>
	<div id="login" class="content-box">
        <blockquote>
      		<div align="center">
				<form name = "Main" action="new_loginprocess.jsp" method="post" onsubmit="return validateForm()">
        			<table border="0" align="center" >
              			<tr>	
              				<th valign="top" colspan="2" align="center" >
              					<p align="center" class="style13">Sign in with your Zimbra account </p>
              				</th>
              			</tr>
              			<tr>
              				<td>              		
                				<p align="center" class="style13">
                					Username:</p>
                			</td>
                			<td>
                				<input name="myusername" size="32" />
                				<br>
                				<%=loginFormHandler.getErrorMsg("myusername")%>
                			</td>
                		</tr>
                		<tr>
                			<td>
                				<p align="center" class="style13">Password:</p>
                			</td>
                			<td>
                				<input name="mypassword" type="password" size="32" />
                				<br>
                				<%=loginFormHandler.getErrorMsg("mypassword")%>
                			</td>
                		</tr>
						<tr>
							<td colspan ="2" align="center">
				  				<input type="submit" value="Sign In" align="middle"/>
				  			</td>
				  		</tr>
				  	</table>
	      		</form>
			</div>
			<p align="center" class="style13">Not a user?</p>
 			<p align="center">
 				<a href="sms.jsp">
                	<button>Register</button>
              	</a>
            </p>
            <p align="center" class="style13">Want to Unregister?</p>
 			<p align="center">
 				<a href="unregister.jsp">
                	<button>Unregister</button>
              	</a>
            </p>
		</blockquote>	
	</div>
	<div id="changepassword" class="content-box" style="display:none">
		<form name= "Main1" action="mobilechangeprocess.jsp" method=post onsubmit="return validateForm5()">
			<center>
				<table cellpadding=4 cellspacing=2 border=0>
					<th colspan=2>
						<font size=5>Change Password</font>
							<br></br>
						<font size=1><sup>*</sup> Required Fields</font>
					</th>
					<tr>
						<td valign=top> 
							Username<sup>*</sup>
						</td>
						<td valign=top> 
							<input type="text" name="passChangeUserName" value="" size=15 maxlength=80 />
							<br></br>
						</td>
					</tr>
					<tr>
						<td valign=top> 
							Old Password<sup>*</sup> 
						</td>
						<td valign=top> 
							<input type="password" name="oldPassword" value="" size=15 maxlength=80 />
							<br></br>
							<!--%=passChangeFormHandler.getErrorMsg("oldPassword")%-->
						</td>
					</tr>
						<tr>
							<td valign=top> 
								New Password<sup>*</sup> 
							</td>
							<td valign=top> 
								<input type="password" name="newPassword1" value="" size=15 maxlength=80 />
								<br></br> 
<!--%=passChangeFormHandler.getErrorMsg("newPassword1")%-->
							</td>
						</tr>
						<tr>
							<td valign=top> 
								Confirm New Password<sup>*</sup> 
							</td>
							<td valign=top> 
								<input type="password" name="newPassword2" value="" size=15 maxlength=80 />
								<br></br>
<!--%=passChangeFormHandler.getErrorMsg("newPassword2")%-->
							</td>
						</tr>
						<tr>
							<td  align=center colspan=2>
								<input type="submit" value="Submit" /> 
								<input type="reset" value="Reset" />
							</td>
						</tr>
					</table>
				</center>
			</form>
		</div>
	<div id="faqs" class="content-box" style="display:none">
				<b>Q. What is the procedure to sign up?</b><br>
A. Click on the "SMS setting button" on the extreme left corner of your zimbra webmail account. The user manual provides a step by step guide to making an account by registering for the first time and then signing up. For more insights into the same, please refer to the user manual, a downloadable link of which is provided alongside. <br> <br>
<b>Q. Where should I make changes so that I could get the messages on my new/another mobile number?</b><br>
A. Unregister from service and then register again.<br><br>
<b>Q. What is the amount that I need to pay to avail the 'SMS Notification' facility?</b><br>
A. An amount of Rs. 30 for a period of one month for 1000 messages must be paid. Renew package if the SMSs get exhausted. <br><br>
<b>Q. What is an MNC account?</b><br>
A. An MNC account is a Message Notification Account on which a user must register to avail the SMS notification facility. The user would have to sign in MNC to edit settings.<br><br> 
<b>Q. If my zimbra password has been reset, what are the changes that I need to make so that I can continue to get the messages?</b><br>
A. Click on the "change password" tab on the login page of MNC and enter the old zimbra/MNC password in the "Old password" text box and then enter the new zimbra password in the "New password" textbox and click on submit.<br><br>
<b>Q. How do I unregister from the MNC account?</b><br>
A. First sign in the MNC account and then unregister by selecting the disable link in the login page of the MNC.<br><br>
<b>Q. What is the software required for MNC to work?</b><br>
A. There is no specific software required for MNC to work. It is supported by most web browsers. Only prior requirement is that the user must be using a zimbra mail-client server.<br><br>
<b>Q. What is the procedure for confirming my mobile number if I have lost the confirmation code?</b><br>
A. A resend button on the confirmation code would cause the confirmation code to be sent to the mobile number registered. This action can be repeated for 5 times at maximum. After which the user would have to wait for another two hours before the resend button is reactivated.<br><br>
<b>Q. What could be the reason if I am not receiving messages despite of having registered my mobile number and having paid for the SMS service?</b><br>
A. This could be because of either or all of the three reasons:
i. Mail quota exceeded on zimbra account: Each institution imposes a quota limit on the space made available to each user. One may not be receiving mails and hence an SMS notification for the same. Some mails may have to be deleted before the functionality is reset.
ii. SMS Quota exceeded: Having paid for an SMS package, it is quite likely that the allotted messages may have exhausted. Either recharge the SMS package by paying the required amount. Another good idea is keep a personal to check on the number of SMS recived and thus, disabling the functionality when SMS is not required to save on the allotted messages.
iii. Do Not Disturb(DND) Enabled: Disable this feature on your phone, to receive messages from MNC.<br><br>
<b>Q. Where could I get all the help that I need for the SMS settings?</b><br>
A. A downloadable user manual is available below.<br><br>
<b>Q. Where could I mail specific queries and send error report?</b><br>
A. daiict.sen5.2011@gmail.com<br><br>	
<centre>
Link for user manual <a href="usermanual.pdf">User manual</a>
</centre>
<br><br>	
		</div>
</body>
</html>