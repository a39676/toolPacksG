//package emailHandle.mailService.revice;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.Properties;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.mail.Address;
//import javax.mail.Folder;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Store;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.search.SearchTerm;
//
//import emailHandle.MailHandle;
//import ioHandle.FileUtilCustom;
//
//public class ReciveEmail {
//	
//	public static void main(String[] args) throws MessagingException {
//		
//		String userName = "@sina.com";
//		String password = "";
//		String sinaImapPropertiesPath = "D:\\wp02\\ssms\\src\\main\\resources\\properties\\email\\sinaImapSsl.properties";
//		String sinaSmtpPropertiesPath = "D:\\wp02\\ssms\\src\\main\\resources\\properties\\email\\sinaSmtpSsl.properties";
//		
//		MailHandle mailHandle = new MailHandle();
//		FileUtilCustom ioUtil = new FileUtilCustom();
//		
//		Properties imapProperties = null;
//		imapProperties = ioUtil.getPropertiesFromFile(sinaImapPropertiesPath);
//		
//		Properties smtpProperties = null;
//		smtpProperties = ioUtil.getPropertiesFromFile(sinaSmtpPropertiesPath);
//		
//		Store store = mailHandle.getMailStore(userName, password, smtpProperties, imapProperties);
//		
//		Folder inbox = mailHandle.getInbox(store);
//		
//		SearchTerm searchTerm = new SearchTerm() {
//			private static final long serialVersionUID = 7873209385471356176L;
//
//			@Override
//			public boolean match(Message message) {
//				Date receiveDate = null;
//				try {
//					receiveDate = message.getReceivedDate();
//				} catch (MessagingException e1) {
//					e1.printStackTrace();
//					return false;
//				}
//				if(receiveDate.before(new Date(System.currentTimeMillis() - (1000L * 60 * 60 * 24 * 333333)))) {
//					return false;
//				}
//				
//				Address[] from = null;
//				try {
//					from = message.getFrom();
//				} catch (MessagingException e) {
//					e.printStackTrace();
//					return false;
//				}
//				if(from == null || from.length < 1) {
//					return false;
//				}
//				boolean flag = false;
//				for(Address f : from) {
//					System.out.println(f);
////					Pattern pattern = Pattern.compile("(^@189.cn)");
////				    Matcher matcher = pattern.matcher(f.toString());
////				    if (matcher.find()) {
////				    	if(matcher.group(1).equals("@189.cn")) {
////				    		flag = true;
////				    	}
////				    } 
//					if(f.toString().equals("@189.cn")) {
//						flag = true;
//					}
//				}
//				if(!flag) {
//					return false;
//				}
//				
//				try {
//					MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
//					String content = mimeMultipart.getBodyPart(0).getContent().toString();
//					System.out.println(content);
//					if(content.contains("")) {
//						return true;
//					}
//				} catch (MessagingException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return false;
//			}
//		};
//		
//		Message[] targetMail = mailHandle.getMailReadOnly(inbox, searchTerm);
//		
//		System.out.println(targetMail[0].getSubject());
//	}
//
//}
