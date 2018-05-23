package emailHandle.mailService.send;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import emailHandle.MailSession;

public class SendEmail {

	/**
	 * 寄送简单文本电子邮件
	 * @param userName
	 * @param password
	 * @param sendTo
	 * @param title
	 * @param content
	 * @param properties (smtp)
	 */
	public void sendSimpleMail(String userName, String password, String sendTo, String title, String content, Properties properties) {

		Session session = MailSession.getSmtpSslSession(properties, userName, password);
		
		Message message = new MailMessageCreator().createSimpleMessage(session, userName, sendTo, title, content);
		
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMailWithAttachment(String userName, String password, String sendTo, String title, String content, List<String> attachmentPathList, Properties properties) {

		Session session = MailSession.getSmtpSslSession(properties, userName, password);
		
		Message message = new MailMessageCreator().createMailWithAttachments(session, userName, sendTo, title, content, attachmentPathList);
		
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(String userName, String password, List<String> sendTo, List<String> cc, List<String>bcc, String subject, String content, List<String> attachmentPathList, Properties properties) {

		Session session = MailSession.getSmtpSslSession(properties, userName, password);
		
		Message message = new MailMessageCreator().createMessage(session, userName, sendTo, cc, bcc, subject, content, attachmentPathList);
		
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}