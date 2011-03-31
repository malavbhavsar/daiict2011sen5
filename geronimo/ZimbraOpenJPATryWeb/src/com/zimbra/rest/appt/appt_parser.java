package com.zimbra.rest.appt;


	import java.io.*;
import java.util.*;
import java.util.logging.Logger;

//import org.xml.sax.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

//import appt.appt;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
public class appt_parser extends DefaultHandler implements java.io.Serializable {

	private Vector vector = new Vector();
	private appt current = null;
	public static final int NOTEXT=0;
	public static final int Fr=1;
	public static final int Desc=2;
	public static final int DescHTML=2;
	private static int STATUS;
	private char cbuf[];
	private static final Logger log = Logger.getLogger(appt_parser.class.getName());
	   public appt_parser() {
	   }

	   public Vector parse(String input)
	   {
	           XMLReader xr = null;
	           try {
	                   xr = XMLReaderFactory.createXMLReader();
	           } catch (SAXException e1) {
	                   // TODO Auto-generated catch block
	                   e1.printStackTrace();
	           }
	           xr.setContentHandler(this);
	           xr.setErrorHandler(this);    
	           try {
	                   xr.parse(new InputSource(new StringReader(input)));
	           } catch (IOException e) {
	                   // TODO Auto-generated catch block
	                   e.printStackTrace();
	           } catch (SAXException e) {
	                                // TODO Auto-generated catch block
	                                e.printStackTrace();
	                        }
	     return vector;
	   }
	   
	   // receive notification of the beginning of an element
	   
	   public void startElement (String uri, String name, String qName, Attributes atts) 
	   {
	          log.info("in START "+name);
	          STATUS=NOTEXT;
	          if(name.equals("items"))
	          {
	                  //Do nothing
	          }
	          else if(name.equals("appt"))
	          {
	                  current = new appt();
	                  //filling the appointment object with the data
	                  current.setAppt_id(Long.parseLong(resolveAttrib(uri,"id",atts,"-1")));
	                  current.setAppt_uid(resolveAttrib(uri,"uid",atts,"u"));
	                  current.setAppt_rev(Long.parseLong(resolveAttrib(uri,"rev",atts,"-1")));
	                  current.setAppt_d(Long.parseLong(resolveAttrib(uri,"d",atts,"-1")));
	                  current.setAppt_s(Integer.parseInt(resolveAttrib(uri,"s",atts,"-1")));
	                  current.setAppt_l(Integer.parseInt(resolveAttrib(uri,"l",atts,"-1")));
	          }
	          else if(name.equals("inv"))
	          {
	        	  //filling invite object of the current appointment object
	        	  	current.invite.setInv_id(Long.parseLong(resolveAttrib(uri,"id",atts,"-1")));
	        	  	current.invite.setInv_seq(Long.parseLong(resolveAttrib(uri,"seq",atts,"-1")));
	        	  	current.invite.setInv_compNum(Long.parseLong(resolveAttrib(uri,"compNum",atts,"-1")));
	        	  	current.invite.setInv_type(resolveAttrib(uri,"type",atts,"-1"));
	          }
	          else if(name.equals("tz"))
	          {
	        	  //filling tz object of the current appointment object
	        	  current.invite.timezone.setTz_id(resolveAttrib(uri,"id",atts,"-1"));
	        	  current.invite.timezone.setTz_stdname(resolveAttrib(uri,"stdname",atts,"-1"));
	        	  current.invite.timezone.setTz_stdoff(Long.parseLong(resolveAttrib(uri,"stdoff",atts,"-1")));
	        	  
	          }
	          else if(name.equals("comp"))
	          {
	        	  current.invite.component.setComp__class(resolveAttrib(uri,"class",atts,"-1"));
	        	  current.invite.component.setComp_uid(resolveAttrib(uri,"uid",atts,"-1"));
	        	  current.invite.component.setComp_status(resolveAttrib(uri,"status",atts,"-1"));
	        	  current.invite.component.setComp_loc(resolveAttrib(uri,"loc",atts,"-1"));
	        	  current.invite.component.setComp_url(resolveAttrib(uri,"url",atts,"-1"));
	        	  current.invite.component.setComp_name(resolveAttrib(uri,"name",atts,"-1"));
	        	  current.invite.component.setComp_x_uid(resolveAttrib(uri,"x_uid",atts,"-1"));
	        	  current.invite.component.setComp_fb(resolveAttrib(uri,"fb",atts,"-1"));
	        	  current.invite.component.setComp_fba(resolveAttrib(uri,"fbs",atts,"-1"));
	        	  current.invite.component.setComp_method(resolveAttrib(uri,"method",atts,"-1"));
	        	  current.invite.component.setComp_transp(resolveAttrib(uri,"transp",atts,"-1"));
	        	  current.invite.component.setComp_d(Long.parseLong(resolveAttrib(uri,"d",atts,"-1")));
	        	  current.invite.component.setComp_compNum(Long.parseLong(resolveAttrib(uri,"compNum",atts,"-1")));
	        	  current.invite.component.setComp_apptId(Long.parseLong(resolveAttrib(uri,"apptId",atts,"-1")));
	        	  current.invite.component.setComp_seq(Long.parseLong(resolveAttrib(uri,"seq",atts,"-1")));
	        	  current.invite.component.setComp_calItemId(Long.parseLong(resolveAttrib(uri,"calItemId",atts,"-1")));
	        	  
	          }
	          else if( name.equals( "at" ) )
	          {
	        	  attendee hold = new attendee();
	        	  hold.setAt_url(resolveAttrib(uri,"url",atts,"-1"));
	        	  hold.setAt_d(resolveAttrib(uri,"d",atts,"-1"));
	        	  hold.setAt_a(resolveAttrib(uri,"a",atts,"-1"));
	        	  hold.setAt_role(resolveAttrib(uri,"role",atts,"-1"));
	        	  hold.setAt_ptst(resolveAttrib(uri,"ptst",atts,"-1"));
	        	  hold.setAt_rsvp(Integer.parseInt(resolveAttrib(uri,"rsvp",atts,"-1")));
	        	  current.invite.component.att.add(hold);
	          }
	          else if( name.equals( "alarm" ) )
	          {
	        	  current.invite.component.alarm_desc.setAlarm_action(resolveAttrib(uri,"action",atts,"-1"));
	          }
	          else if( name.equals( "rel" ) )
	          {
	        	  current.invite.component.alarm_desc.alm_trig_rel.setTri_rel_m(Integer.parseInt(resolveAttrib(uri,"m",atts,"-1")));
	        	  current.invite.component.alarm_desc.alm_trig_rel.setTri_rel_neg(Integer.parseInt(resolveAttrib(uri,"neg",atts,"-1")));
	        	  current.invite.component.alarm_desc.alm_trig_rel.setRelated(resolveAttrib(uri,"related",atts,"-1"));  
	          }
	          else if( name.equals( "fr" ) )
	          {
	        	  STATUS=Fr;
	          }

	          else if( name.equals( "desc" ) )
	          {
	        	  STATUS = Desc;
	          }

	          else if( name.equals( "descHTML" ) )
	          {
	        	  STATUS = DescHTML;
	          }
	          else if( name.equals( "or" ) )
	          {
	        	  current.invite.component.or.setOr_d(resolveAttrib(uri,"d",atts,"-1"));
	        	  current.invite.component.or.setOr_a(resolveAttrib(uri,"a",atts,"-1"));
	        	  current.invite.component.or.setOr_url(resolveAttrib(uri,"url",atts,"-1"));
	          }
	          else if( name.equals( "s" ) )
	          {
	        	  current.invite.component.setStartdate(resolveAttrib(uri,"d",atts,"-1"));
	        	  current.invite.component.setStartdate_tz(resolveAttrib(uri,"tz",atts,"-1"));
	          }
	          else if( name.equals( "e" ) )
	          {
	        	  current.invite.component.setEnddate(resolveAttrib(uri,"d",atts,"-1"));
	        	  current.invite.component.setEnddate_tz(resolveAttrib(uri,"tz",atts,"-1"));
	            
	          }
	          else if( name.equals( "rule" ) )
	          {
	        	  current.invite.component.recur_info.setRecur_rule_freq(resolveAttrib(uri,"freq",atts,"-1"));
	          }
	          else if( name.equals( "until" ) )
	          {
	        	  current.invite.component.recur_info.setRecur_until_date(Integer.parseInt(resolveAttrib(uri,"d",atts,"-1")));
	          }
	          else if( name.equals( "interval" ) )
	          {
	        	  current.invite.component.recur_info.setRecur_interval(Integer.parseInt(resolveAttrib(uri,"ival",atts,"-1")));
	          }
	          else if( name.equals( "replies" ) )
	          {
	        	  //current.setNotes( );
	          }
	          else
	          {
	                  //Exception
	          }
	   }

	   // receive notification of the end of an element
	   public void endElement (String uri, String name, String qName) 
	   {
	          log.info("in END "+name);

	          if(name.equals("items"))
	          {
	                  //Do nothing
	          }
	          else if(name.equals("appt"))
	          {
	              vector.addElement(current);
	                  current=null;
	          }
	          else if(name.equals("inv"))
	          {
	          }
	          else if(name.equals("alarm"))
	          {
	          }
	          else if(name.equals("comp"))
	          {
	          }
	          else if(name.equals("fr"))
	          {
	        	  STATUS=NOTEXT;
	          }
	          else if(name.equals("Desc"))
	          {
	        	  STATUS=NOTEXT;
	          }
	          else if(name.equals("DescHTML"))
	          {
	        	  STATUS=NOTEXT;
	          }
	          else
	          {
	                  //Exception
	          }
	   }
	   
	   // receive notification of character data
	   public void characters (char ch[], int start, int length) {     
	                  log.info("in TEXT "+new String(ch,start,length));

	         if(STATUS==NOTEXT) 
	     {
	        //DO NOTHING
	     }
	     else if(STATUS==Fr)
	     {
	         String temp = new String(ch, start, length);
	         current.invite.component.setNotes_fr(temp);
	     }
	     else if(STATUS==Desc)
	     {
	         String temp = new String(ch, start, length);
	         current.invite.component.setDesc(temp);
	     }
	     else if(STATUS==DescHTML)
	     {
	         String temp = new String(ch, start, length);
	         current.invite.component.setDeschtml(temp);   
	     }  
	     else
	     {
	         //DO NOTHING
	     }
	   }
	   
	   private String resolveAttrib( String uri, String localName, Attributes attribs, String defaultValue ) 
	   {
	                String tmp = attribs.getValue( uri, localName );
	                return (tmp!=null)?(tmp):(defaultValue);
	   }

}
