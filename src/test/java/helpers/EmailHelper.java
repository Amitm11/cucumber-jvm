package helpers;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class EmailHelper
{
	 public static void sendEmailWithAttachments(String host, String port, final String userName, final String password, 
			 String[] toAddress, String subject, String message, String attachFile) throws AddressException, MessagingException
	 {
//		sets SMTP server properties
		Properties properties = new Properties();
		properties = System.getProperties();
		properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        try
        {
	//	    creates a new session with an authenticator
		    Authenticator auth = new Authenticator() 
		    {
		    	public PasswordAuthentication getPasswordAuthentication() 
		    	{
		    		return new PasswordAuthentication(userName, password);
		    	}
		    };
		        
		    Session session = Session.getDefaultInstance(properties, auth);
	
		    //	 	creates a new e-mail message
		    MimeMessage msg = new MimeMessage(session);
		 
		    msg.setFrom(new InternetAddress(userName));
		    
		    InternetAddress[] toAddr = new InternetAddress[toAddress.length];
		    for (int i = 0; i < toAddress.length; i++)
		    {
		    	toAddr[i] = new InternetAddress(toAddress[i]);
		    }
		    
		    for (int i = 0; i < toAddr.length; i++)
		    {
		    	msg.addRecipient(RecipientType.TO, toAddr[i]);
		    }
		    
		    msg.setSubject(subject);
		    msg.setSentDate(new Date());
		   
		    // creates message part
		    MimeBodyPart messageBodyPart = new MimeBodyPart();
		    messageBodyPart.setContent(message, "text/html");
		 
		    // creates multi-part
		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
		    // adds attachments
		    if (!attachFile.isEmpty()) 
		    {
	    		MimeBodyPart attachPart = new MimeBodyPart();
	    		try
	    		{
	    			attachPart.attachFile(attachFile);
	            } catch (IOException ex) 
	    		{
	            	ex.printStackTrace();
	    		}
	            multipart.addBodyPart(attachPart);
		    }
		 
		    // sets the multi-part as e-mail's content
		    msg.setContent(multipart);
		    msg.saveChanges();
		   
		    // sends the e-mail
		    Transport transport = session.getTransport("smtp");
		    transport.connect(host, userName, password);
		    transport.sendMessage(msg, msg.getAllRecipients());
		    transport.close();
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
	 }
	 
	/**
	 * Test sending e-mail with attachments
	 */
	public static void sendEmail(String evdFile) throws Throwable 
	{
		    // SMTP info
		String host = "smtp.gmail.com"; //"mail.experisindia.com";
		String port = "587"; //"25";
		String mailFrom = "amitk.maheshwari"; //"amaheshwari@experisindia.com";
		String password = "amit2010"; //"experis1234";
		 
		        // message info
		String[] mailTo = {"amit.maheshwari@experis.com", "amaheshwari@experisindia.com"};
		String subject = "Automated test execution report with evidence";
		String message = "<font size =\"5\" face=\"arial\" >The attached file created by automation script, contains screenshots of the steps performed "
				+ "during execution which are captured by the automation script. Therefore, there is no scope of manual "
				+ "intervention and manipulation.</font>";
		 
		        // attachments
		String attachFile = evdFile;
		 
		try 
		{
			sendEmailWithAttachments(host, port, mailFrom, password, mailTo, subject, message, attachFile);
			System.out.println("Email sent.");
		} catch (Exception ex)
		{
		    System.out.println("Could not send email.");
		    ex.printStackTrace();
		}
	}
}
