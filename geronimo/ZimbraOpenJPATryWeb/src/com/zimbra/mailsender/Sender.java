package com.zimbra.mailsender;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String EMAIL_FROM_ADDRESS = "daiict.sen5.2011@gmail.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private static final String SENDER_USER = "daiict.sen5.2011";
    private static final String SENDER_PASS = "malavbhavsar";

    public static enum MAILTYPE {
	WELCOME,
	PASSWORD_CHANGED
    };

    private static final String WELCOME_SUBJECT = "Welcome! Registered with MNC!";
    private static final String WELCOME_MAIL_BODY = "Hi,\n"
	    + "You have been registered with MNC.\n" 
	    + "\n"
	    + "For queries, please write us at "
	    + "daiict.sen5.2011@gmail.com\n" 
	    +"\n" 
	    + "Regards,\n"
	    + "SEN team 5";
    
    private static final String PASSWORD_CHANGED_SUBJECT = "Webmail password changed!";
    private static final String PASSWORD_CHANGED_MAIL_BODY = "Hi,\n"
	    + "You have changed your password of webmail.\n" + "\n"
	    + "To continue having our services, please login "
	    + "with your old password on our site. And use "
	    + "change password service.\n" + "\n"
	    + "For further queries, please write us at "
	    + "daiict.sen5.2011@gmail.com\n" + "\n" + "Regards,\n"
	    + "SEN team 5";

    public static void sendEmail(ArrayList<String> sendTo, MAILTYPE m)
	    throws Exception {
	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	switch (m) {
	case PASSWORD_CHANGED:
	    new Sender().sendSSLMessage(sendTo, PASSWORD_CHANGED_SUBJECT,
		    PASSWORD_CHANGED_MAIL_BODY, EMAIL_FROM_ADDRESS);
	    System.out.println("----------------Sucessfully Sent mail to All Users----------------");
	}
    }

    public void sendSSLMessage(ArrayList<String> recipients, String subject,
	    String message, String from) throws MessagingException {
	boolean debug = true;

	Properties props = new Properties();
	props.put("mail.smtp.host", SMTP_HOST_NAME);
	props.put("mail.smtp.auth", "true");
	props.put("mail.debug", "true");
	props.put("mail.smtp.port", SMTP_PORT);
	props.put("mail.smtp.socketFactory.port", SMTP_PORT);
	props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
	props.put("mail.smtp.socketFactory.fallback", "false");
	    System.out.println("----------------Here1----------------");

	Session session = Session.getDefaultInstance(props,
		new javax.mail.Authenticator() {

		    protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(SENDER_USER,
				SENDER_PASS);
		    }
		});

	session.setDebug(debug);
	    System.out.println("----------------Here2----------------");

	Message msg = new MimeMessage(session);
	InternetAddress addressFrom = new InternetAddress(from);
	msg.setFrom(addressFrom);
	    System.out.println("----------------Here3----------------");

	InternetAddress[] addressTo = new InternetAddress[recipients.size()];
	for (int i = 0; i < recipients.size(); i++) {
	    addressTo[i] = new InternetAddress(recipients.get(i));
	}
	    System.out.println("----------------Here4----------------");

	msg.setRecipients(Message.RecipientType.TO, addressTo);

	// Setting the Subject and Content Type
	msg.setSubject(subject);
	msg.setContent(message, "text/plain");
	Transport.send(msg);
	    System.out.println("----------------Here5----------------");

    }
}