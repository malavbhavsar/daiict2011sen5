package com.zimbra.SMS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class SMSSender {	
	/*	
    1	Create a URL. 
	2	Retrieve the URLConnection object. 
	3	Set output capability on the URLConnection. 
	4	Open a connection to the resource. 
	5	Get an output stream from the connection. 
	6	Write to the output stream. 
	7	Close the output stream.
	*/
    
    public SMSSender(){
	
    }
	public static void sendSMS(SMS sms) throws Exception{
		String postData="";
		String retval = "";

		//give all Parameters In String
		String User ="User_Name";//Username of sushant
		String passwd = "Password";//Password of sushant
		String mobilenumber = sms.getMobileNumber(); 
		String message = sms.getContent();
		String sid = "Sender_Id";
		String mtype = "N";
		String DR = "Y";		

		
		
		postData += "User=" + URLEncoder.encode(User,"UTF-8") + "&passwd=" + passwd + "&mobilenumber=" + mobilenumber + "&message=" + URLEncoder.encode(message,"UTF-8") + "&sid=" + sid + "&mtype=" + mtype + "&DR=" + DR;
		URL url = new URL("http://smscountry.com/SMSCwebservice.asp");
		HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();

		// If You Are Behind The Proxy Server Set IP And PORT else Comment Below 4 Lines
		//Properties sysProps = System.getProperties();
		//sysProps.put("proxySet", "true");
		//sysProps.put("proxyHost", "Proxy Ip");
		//sysProps.put("proxyPort", "PORT");

		urlconnection.setRequestMethod("POST");
		urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		urlconnection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
		out.write(postData);
		out.close();
		BufferedReader in = new BufferedReader(	new InputStreamReader(urlconnection.getInputStream()));
		String decodedString;
		while ((decodedString = in.readLine()) != null) {
			retval += decodedString;
		}
		in.close();
		System.out.println(retval);
	}
}

