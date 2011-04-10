<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="java.util.*"%>
<%@page import="com.zimbra.ui.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%  
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("m");
	EntityManager em = emf.createEntityManager();
	System.out.println("gg");
	System.out.println(session.getAttribute("username").toString());
	UserSettingsHandler broker = new UserSettingsHandler(session.getAttribute("username").toString());
	System.out.println(session.getAttribute("username").toString());

	ArrayList<String> userList= broker.getAllUser();
	ArrayList<String> subjectList= broker.getAllSubject();
	String selectedString = "";

	String buttonName=request.getParameter("userButton");
	if(buttonName != null && !buttonName.isEmpty())
	{
		if(buttonName.equalsIgnoreCase("Add"))
		{
			String name = request.getParameter("excludeUserName");
			broker.addUser(name);
		}
		else if(buttonName.equalsIgnoreCase("Delete"))
		{
			String name = request.getParameter("userList");
			broker.deleteUser(name);
		}
	}
	
	String buttonName2=request.getParameter("subjectButton");
	if(buttonName2 != null && !buttonName2.isEmpty())
	{
		if(buttonName2.equalsIgnoreCase("Add"))
		{
			String name = request.getParameter("subjectName");
			broker.addSubject(name);
		}
		else if(buttonName2.equalsIgnoreCase("Delete"))
		{
			String name = request.getParameter("subjectList");
			broker.deleteSubject(name);
		}
	}
	
	userList= broker.getAllUser();
	subjectList= broker.getAllSubject();
 %>
<script type="text/javascript">
    var currentCat = "";
	function showUsers(cat)
	{
		var options=
	        <%
	        out.print("\"<select size=15 style=\\\"width: 200px\\\" name=userList>");
			
			List<String> list = userList;
	        for(int i=0; i < list.size(); i++)
	        {
	        	out.print("<option value=\\\"" + 
	        			list.get(i) + "\\\">" + list.get(i) + "</option>");
	        }
	        out.print("</select>\";");
	        %>
		if(cat == "")
		{
			cat=options;
		}
		document.write(cat);
   	}
	function showSubjects(cat)
	{
		var options=
	        <%
	        out.print("\"<select size=15 style=\\\"width: 200px\\\" name=subjectList>");
			
			list = subjectList;
	        for(int i=0; i < list.size(); i++)
	        {
	        	out.print("<option value=\\\"" + 
	        			list.get(i) + "\\\">" + list.get(i) + "</option>");
	        }
	        out.print("</select>\";");
	        %>
		if(cat == "")
		{
			cat=options;
		}
		document.write(cat);
   	}
</script>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title>MNC</title>
	<link rel="stylesheet" href="new_page.css" type="text/css"/>
	<script type="text/javascript" src="jquery.min.js"></script>
	<script type="text/javascript" src="new_page.js"></script>
	<script type="text/javascript">
			var activeDone='settings';
	</script>
		
	
	
	</head>
		<body>
			<table align="center">
			<tr>
			<td>
				<img src="mnc1.jpg" align="left" height="100" width="240" alt="mnc" />
				</td>
				<td>
  				<a href = "new_logout.jsp">Sign Out</a>
  				</td>
			</tr>
			</table>		
			<div id="done-nav" style="display:inline">
				<ul>
					<li><label title="home"  id="home-nav">Home</label></li>
					<li><label title="settings" class="active" id="settings-nav">Settings</label></li>
            		<li><label title="faqs"  id="faqs-nav">FAQ's</label></li>
            		<li><label title="aboutus"  id="aboutus-nav">About us</label></li>
				</ul>
			</div>
			<div id="home" class="content-box" style="display:none">		
				<% String url = request.getRequestURI(); 
					%>		
				Welcome <%= session.getAttribute("username") %> !			
			</div>
			<div id="settings" class="content-box" >
				Settings of <%= session.getAttribute("username") %> !				
				<table border = 0>
					<tr>
						<td>
							<form action="<%= url %>" method=post>
								<center>
									<table border=1 cellpadding=3>
										<tr>
											<th colspan=2 style="color: White; background-color: Blue;">Exclude by Username</th>
										</tr>
										<tr>
											<td rowspan="2">
												<script type="text/javascript">
        											showUsers("");
        										</script>
        									</td>
											<td>
												Username or email address
												<br></br>
												<input name=excludeUserName type=text id=excludeUserName/>
												<br></br>
											</td>
										</tr>
										<tr>
											<td colspan=2>
												<input type=submit name=userButton value="Add"/> 
												<input type=submit name=userButton value="Delete"/>
											</td>
										</tr>
									</table>
								</center>
							</form>
						</td>
						<td>
							<form action="<%= url %>" method=post>
								<center>
									<table border=1 cellpadding=3>
										<tr>
											<th colspan=2 style="color: White; background-color: Blue;">Exclude by Subject keyword</th>
										</tr>
										<tr>
											<td rowspan="2">
												<script type="text/javascript">
        											showSubjects("");
        										</script>
        									</td>
											<td>
												Subject keyword
												<br></br>
												<input name=subjectName type=text id=subjectName/>
												<br></br>	
											</td>
										</tr>
										<tr>
											<td colspan=2>
												<input type=submit name=subjectButton value="Add"/>
												<input type=submit name=subjectButton value="Delete"/>
											</td>
										</tr>
									</table>
								</center>
							</form>
						</td>
					</tr>
				</table>
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
		<div id="aboutus" class="content-box" style="display:none">
			<p>
				The DA-IICT SEN Team-5 is a group of coders and designers (who are doing their BTech. in ICT at DA-IICT) that intend to make common, user friendly features available on standard platforms for free. We are motivated by openness to new ideas and innovative implementation, besides participation.
			</p>
			<p>
				The projects developed by the team are open for incremental revision and/or improvement. The first venture by the group involved the development of an application that would enable SMS Notification for webmail for the DA-IICT (Dhirubhai Ambani Institute of Information and Communication Technology, Gandhinagar) community.
			</p>
			<br><br>
			<center>								
				<img src="grouppic.jpg" /> 
			</center>						
		</div>
	</body>
</html>