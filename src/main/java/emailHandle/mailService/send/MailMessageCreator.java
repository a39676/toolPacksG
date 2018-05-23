package emailHandle.mailService.send;

import java.util.Arrays;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;

public class MailMessageCreator {

	public Message createMessage(Session session, String sendFrom, List<String> sendTo, List<String> cc, List<String> bcc, String subject, String content, List<String> filePaths) {
		Message message = null;
		Multipart attachement = new MailAttachment().createAttachments(content, filePaths);
		
		try {
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendFrom));
			if(sendTo != null && sendTo.size() > 0) {
				for(String s : sendTo) {
					if(StringUtils.isNotBlank(s)) {
						message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(s));
					}
				}
			} else {
				return null;
			}
			
			if(cc != null && cc.size() > 0) {
				for(String s : cc) {
					if(StringUtils.isNotBlank(s)) {
						message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(s));
					}
				}
			}
			
			if(bcc != null && bcc.size() > 0) {
				for(String s : bcc) {
					if(StringUtils.isNotBlank(s)) {
						message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(s));
					}
				}
			}
			
			if(StringUtils.isNotBlank(subject)) {
				message.setSubject(subject);
			}
			if(StringUtils.isNotBlank(content)) {
				message.setText(content);
			}
			
			message.setContent(attachement);

			return message;

		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Message createSimpleMessage(Session session, String sendFrom, String sendTo, String subject, String text) {
		return createMessage(session, sendFrom, Arrays.asList(sendTo), null, null, subject, text, null);
	}
	
	public Message createSimpleMessage(Session session, String sendFrom, String sendTo, String bcc, String subject, String text) {
		return createMessage(session, sendFrom, Arrays.asList(sendTo), null, Arrays.asList(bcc), subject, text, null);
	}
	
	public Message createMailWithAttachments(Session session, String sendFrom, String sendTo, String subject, String text, List<String> filePaths) {
		return createMessage(session, sendFrom, Arrays.asList(sendTo), null, null, subject, text, filePaths);
	}
	
	public Message createMailWithAttachments(Session session, String sendFrom, String sendTo, String bcc, String subject, String text, List<String> filePaths) {
		return createMessage(session, sendFrom, Arrays.asList(sendTo), null, Arrays.asList(bcc), subject, text, filePaths);
	}
	
}
