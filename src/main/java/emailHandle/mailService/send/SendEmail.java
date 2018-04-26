package emailHandle.mailService.send;

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
//		List<String> attachmentsList = new ArrayList<String>();
//		attachmentsList.add("D:/auxiliary/tmp/tmp.txt");
//		attachmentsList.add("D:/auxiliary/tmp/tmp.html");
//		attachmentsList.add("D:/auxiliary/tmp/tmp0.txt");
//		attachmentsList.add("D:/auxiliary/tmp/tmp.jsp");
//		attachmentsList.add("D:/auxiliary/tmp/tmp2.txt");
//		Message message = new MailMessageCreator().createMailWithAttachments(session, sendFrom, sendTo, "测试标题3", "测试附件2", attachmentsList);
		
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}