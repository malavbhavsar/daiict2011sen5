package com.zimbra.SMS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
	public static void sendSMS(SMS sms){
	    	System.out.println("debug -----"+sms.getContent());
		String postData="";
		String retval = "";

		//give all Parameters In String
		String User ="sush38";//Username of sushant
		String passwd = "ictengineering";//Password of sushant
		String mobilenumber = sms.getMobileNumber(); 
		String message = sms.getContent();
		String sid = "damnc";
		String mtype = "N";
		String DR = "Y";		
		System.out.print("debug2----"+sms.getMobileNumber());
		
		try {
		    postData += "User=" + URLEncoder.encode(User,"UTF-8") + "&passwd=" + passwd + "&mobilenumber=" + mobilenumber + "&message=" + URLEncoder.encode(message,"UTF-8") + "&sid=" + sid + "&mtype=" + mtype + "&DR=" + DR;
		} catch (UnsupportedEncodingException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		URL url = null;
		try {
		    url = new URL("http://smscountry.com/SMSCwebservice.asp");
		} catch (MalformedURLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		HttpURLConnection urlconnection = null;
		try {
		    urlconnection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		// If You Are Behind The Proxy Server Set IP And PORT else Comment Below 4 Lines
		//Properties sysProps = System.getProperties();
		//sysProps.put("proxySet", "true");
		//sysProps.put("proxyHost", "Proxy Ip");
		//sysProps.put("proxyPort", "PORT");
		System.out.print("smshere 3");

		try {
		    urlconnection.setRequestMethod("POST");
		} catch (ProtocolException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		urlconnection.setDoOutput(true);
		System.out.print("smshere 4");

		OutputStreamWriter out = null;
		try {
		    out = new OutputStreamWriter(urlconnection.getOutputStream());
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		System.out.print("smshere 4.1");
		try {
		    out.write(postData);
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		System.out.print("smshere 4.2");
		try {
		    out.close();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		System.out.print("smshere 4.3");
		BufferedReader in = null;
		try {
		    in = new BufferedReader(	new InputStreamReader(urlconnection.getInputStream()));
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		System.out.print("smshere 4.4");
		String decodedString;
		System.out.print("smshere 5");

		try {
		    while ((decodedString = in.readLine()) != null) {
		    	retval += decodedString;
		    }
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		System.out.print("smshere 6");
		try {
		    in.close();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		System.out.println("The reply from SMS");
		System.out.println(retval);
		System.out.print("smshere 7");
	}
}

