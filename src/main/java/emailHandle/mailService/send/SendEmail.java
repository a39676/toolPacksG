package emailHandle.mailService.send;

import java.util.ArrayList;
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
	 * @param context
	 * @param properties (smtp)
	 */
	public void sendSimpalMail(String userName, String password, String sendTo, String title, String context, Properties properties) {

		Session session = MailSession.getSmtpSslSession(properties, userName, password);
		
		Message message = new MailMessageCreator().createSimpleMessage(session, userName, sendTo, title, context);
		
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMailWithAttachment(String userName, String password, String sendTo, String title, String context, List<String> attachmentPathList, Properties properties) {

		Session session = MailSession.getSmtpSslSession(properties, userName, password);
		
		List<String> attachmentsList = new ArrayList<String>();
		
		for(String attachementPath : attachmentPathList) {
			attachmentsList.add(attachementPath);
		}
		
		Message message = new MailMessageCreator().createMailWithAttachments(session, userName, sendTo, title, context, attachmentsList);
		
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}