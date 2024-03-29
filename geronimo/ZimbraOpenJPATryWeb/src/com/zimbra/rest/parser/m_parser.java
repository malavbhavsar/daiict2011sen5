package com.zimbra.rest.parser;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

//import org.xml.sax.*;
import org.apache.openjpa.persistence.EntityExistsException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.zimbra.rest.m.m;
import com.zimbra.user.zuser;

import javax.persistence.EntityManager;

public class m_parser extends DefaultHandler implements java.io.Serializable {
    private m current = null;
    public static final int NOTEXT = 0;
    public static final int SU = 1;
    public static final int FR = 2;
    private static int STATUS;
    private char cbuf[];
    private static final Logger log = Logger
	    .getLogger(m_parser.class.getName());
    private EntityManager em;
    private zuser z;

    public m_parser() {
    }

    // receive notification of character data
    @Override
    public void characters(char ch[], int start, int length) {
	log.info("in TEXT " + new String(ch, start, length));

	if (STATUS == NOTEXT) {
	    // DO NOTHING
	} else if (STATUS == SU) {
	    String temp = new String(ch, start, length);
	    if(temp==null || temp.equals(""))
	    {
		temp="(no subject)";
	    }
	    current.setSu(temp);
	} else if (STATUS == FR) {
	    String temp = new String(ch, start, length);
	    if(temp==null || temp.equals(""))
	    {
		temp="(no body)";
	    }
	    current.setFr(temp);
	} else {
	    // DO NOTHING
	}
    }
    // receive notification of the end of an element
    @Override
    public void endElement(String uri, String name, String qName) {
	log.info("in END " + name);

	if (name.equals("items")) {
	    // Do nothing
	} else if (name.equals("m")) {
	    List l = (em
		    .createNativeQuery("select * from m where m.id="
			    + current.getId()
			    + "AND m.gid_zuser=(select gid_zuser from zuser where zuser.username='"
			    + z.getUsername() + "')")).getResultList();
	    if (l.size() == 0) {
		em.getTransaction().begin();
		em.persist(current);
		z.addM(current);
		em.persist(z);
		em.flush();
		em.getTransaction().commit();
	    }
	    current = null;
	} else if (name.equals("e")) {
	} else if (name.equals("su")) {
	    STATUS = NOTEXT;
	} else if (name.equals("fr")) {
	    STATUS = NOTEXT;
	} else {
	    // Exception
	}
    }

    public void parse(String input, zuser z, EntityManager em) {
	this.em = em;
	this.z = z;
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
    }
    
    // receive notification of the beginning of an element
    @Override
    public void startElement(String uri, String name, String qName,
	    Attributes atts) {
	log.info("in START " + name);

	STATUS = NOTEXT;
	{

	    if (name.equals("items")) {
		// Do nothing
	    } else if (name.equals("m")) {
		current = new m();
		current.setIfSent(false);
		current.setZuserInstance(z);
		current.setId(Integer.parseInt(resolveAttrib(uri, "id", atts,
			"-1")));
		current.setF(resolveAttrib(uri, "f", atts, "u"));
		current.setRev(Integer.parseInt(resolveAttrib(uri, "rev", atts,
			"-1")));
		current.setD(resolveAttrib(uri, "d", atts, "-1"));
		current.setS(Integer.parseInt(resolveAttrib(uri, "s", atts,
			"-1")));
		current.setL(Integer.parseInt(resolveAttrib(uri, "l", atts,
			"-1")));
		current.setCid(Integer.parseInt(resolveAttrib(uri, "cid", atts,
			"-1")));

	    } else if (name.equals("e")) {
		current.setDine(resolveAttrib(uri, "d", atts, "unknown"));
		current.setTine(resolveAttrib(uri, "t", atts, "unknown"));
		current.setAine(resolveAttrib(uri, "a", atts, "unknown"));
	    } else if (name.equals("su")) {
		STATUS = SU;
	    } else if (name.equals("fr")) {
		STATUS = FR;
	    } else {
		// Exception
	    }
	}
    }

    private String resolveAttrib(String uri, String localName,
	    Attributes attribs, String defaultValue) {
	String tmp = attribs.getValue(uri, localName);
	return (tmp != null) ? (tmp) : (defaultValue);
    }
}
