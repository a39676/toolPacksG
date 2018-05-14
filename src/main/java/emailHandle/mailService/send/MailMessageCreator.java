package emailHandle.mailService.send;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailMessageCreator {

	public Message createSimpleMessage(Session session, String sendFrom, String sendTo, String subject, String text) {
		Message message = null;
		
		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
			message.setSubject(subject);
			message.setText(text);

			return message;

		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public Message createMailWithAttachments(Session session, String sendFrom, String sendTo, String subject, String text, List<String> fileNames) {
		Message message = null;
		Multipart attachement = new MailAttachment().createAttachments(fileNames);
		
		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendTo));
			message.setSubject(subject);
			message.setText(text);
			
			message.setContent(attachement);

			return message;

		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
