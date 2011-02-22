package com.zimbra.rest.parser;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import biz.source_code.base64Coder.Base64Coder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.*;

import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;


@SuppressWarnings("serial")
public class Zimbra_rest_parserServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(Zimbra_rest_parserServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		  
		  resp.setContentType("text/plain");
	      String file = new String("inbox.xml");
	      parser p = new parser();
	      String str = new String();
	      //Authentication code!!
	      


	      /*
	      
	        try {
	        	URLFetchService fetcher = (new URLFetchServiceFactory()).getURLFetchService();
	        	
	            URL url = new URL ("http://webmail.daiict.ac.in/home/200801054/inbox.xml?auth=ba&query=is:unread");
	            String encoding = Base64Coder.encodeString("200801054:ernstnyoung");
	            
	            HTTPRequest request = new HTTPRequest(url,HTTPMethod.GET,FetchOptions.Builder.followRedirects().doNotValidateCertificate());
	            HTTPHeader header1 = new HTTPHeader("Authorization", "Basic " + encoding);
	            request.addHeader(header1);
	           
	            HTTPResponse response=fetcher.fetch(request);
	            log.info(new String(response.getContent()));
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	      */
	      
	      try {
		         Collection v = p.parse(getXMLStream("http://webmail.daiict.ac.in/home/200801054/inbox.xml?query=is:unread"));
		         Iterator it = v.iterator();	         
		         while(it.hasNext()) {
		            m element = (m)it.next();
		            
		            str+=element.getInstanceOfe().getD();
		            str+=System.getProperty("line.separator");
		            str+=element.getSu();
		            str+=System.getProperty("line.separator");
		            str+=element.getFr();
		            str+=System.getProperty("line.separator");
		         }
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
		resp.getWriter().println(str);
		//driving method
	}
	
	private String getXMLStream(String siteurl)
	{
		// ...
        InputStreamReader reader=null;
		HTTPResponse response = null;
	        try {
	        	URLFetchService fetcher = (new URLFetchServiceFactory()).getURLFetchService();
	        	
	            URL url = new URL ("http://webmail.daiict.ac.in/home/200801054/inbox.xml?auth=ba&query=is:unread");
	            String encoding = Base64Coder.encodeString("200801054:ernstnyoung");
	            
	            HTTPRequest request = new HTTPRequest(url,HTTPMethod.GET,FetchOptions.Builder.followRedirects().doNotValidateCertificate());
	            HTTPHeader header1 = new HTTPHeader("Authorization", "Basic " + encoding);
	            request.addHeader(header1);
	           
	            response=fetcher.fetch(request);
	            
	    
	            if (response.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                // OK
	            } else {
	                // Server returned HTTP error code.
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

			return new String(response.getContent());
	}
	

}
