package com.zimbra.rest.parser;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import biz.source_code.base64Coder.Base64Coder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Zimbra_rest_parserServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(Zimbra_rest_parserServlet.class.getName());
	private InputStream isr;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		  
		  resp.setContentType("text/plain");
	      parser p = new parser();
	      String str = new String();
	      //Authentication code!!
	      
	      try {
		         Collection v = p.parse(getXMLStream());
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
	
	private String getXMLStream()
	{
		// ...
        InputStreamReader reader=null;
	        try {
	        	
	            URL url = new URL ("https://webmail.daiict.ac.in/home/200801054/inbox.xml?query=is:unread");
	            String encoding = Base64Coder.encodeString("200801054:ernstnyoung");
	            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	            conn.setDoInput(true);
	            conn.setRequestMethod("GET");
	            conn.addRequestProperty("Authorization", "Basic " + encoding);
	            conn.setHostnameVerifier(new NullHostnameVerifier());
	            conn.connect();
	            isr = conn.getInputStream();

	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        log.info("1");

	        String s = "";
			try {
				s = new String(convertStreamToString(isr));
		        System.out.println("the string -----"+s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return s;
	}
			public String convertStreamToString(InputStream is)
	            throws IOException {
	        /*
	         * To convert the InputStream to String we use the
	         * Reader.read(char[] buffer) method. We iterate until the
	         * Reader return -1 which means there's no more data to
	         * read. We use the StringWriter class to produce the string.
	         */
	        if (is != null) {
	            Writer writer = new StringWriter();
	 
	            char[] buffer = new char[1024];
	            try {
	                Reader reader = new BufferedReader(
	                        new InputStreamReader(is, "UTF-8"));
	                int n;
	                while ((n = reader.read(buffer)) != -1) {
	                    writer.write(buffer, 0, n);
	                }
	                System.out.println("printing buffer: "+buffer);
	            } finally {
	                is.close();
	            }
	            return writer.toString();
	        } else {  
	        	System.out.println("It's null!");
	            return "";
	        }
	    }
			
		private static class NullHostnameVerifier implements HostnameVerifier {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		}
}
