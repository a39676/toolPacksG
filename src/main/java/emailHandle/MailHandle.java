package emailHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;

public class MailHandle {

	/**
	 * 登录邮箱
	 * @param userName
	 * @param password
	 * @param smtpProps
	 * @param imapProps
	 * @return
	 */
	public Store getMailStore(String userName, String password, Properties smtpProps, Properties imapProps) {
		Store store;
		Session session = Session.getDefaultInstance(smtpProps, null);
		
		try {
			store = session.getStore("imaps");
			store.connect(imapProps.getProperty(MailConstants.imapHost), userName, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return store;
	}
	
	/**
	 * 获取邮箱所有文件夹
	 * @param store
	 * @return
	 * @throws MessagingException
	 */
	public Folder[] getFolders(Store store) throws MessagingException {
		if(store.isConnected()) {
			return store.getDefaultFolder().list();
		} else {
			return null;
		}
	}
	
	/**
	 * 获取inbox 文件夹
	 * @return
	 */
	public Folder getInbox(Store store) {
		if(store.isConnected()) {
			try {
				return store.getFolder(MailConstants.inbox);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} 
		return null;
	}
	
	/**
	 * 获取指定文件夹
	 * @param folderName
	 * @return
	 */
	public Folder getFolder(Store store, String folderName) {
		if(store.isConnected()) {
			try {
				return store.getFolder(folderName);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} 
		return null;
	}
	
	/**
	 * 只读模式收取邮件
	 * @param folder
	 * @return
	 */
	public Message[] getMailReadOnly(Folder folder) {
		try {
			folder.open(Folder.READ_ONLY);
			return folder.getMessages();
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 按搜索条件收取邮件
	 * @param folder
	 * @param searchTerm
	 * @return
	 */
	public Message[] getMailReadOnly(Folder folder, SearchTerm searchTerm) {
		try {
			folder.open(Folder.READ_ONLY);
			return folder.search(searchTerm);
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取发件人
	 * @param mail
	 * @return
	 */
	public Address[] getFromerAddress(Message mail) {
		try {
			return mail.getFrom();
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取收件人
	 * @param mail
	 * @return
	 */
	public Address[] getToAddress(Message mail) {
		try {
			return mail.getRecipients(RecipientType.TO);
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取秘抄送收件人
	 * @param mail
	 * @return
	 */
	public Address[] getBCCAddress(Message mail) {
		try {
			return mail.getRecipients(RecipientType.BCC);
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取抄送收件人
	 * @param mail
	 * @return
	 */
	public Address[] getCCAddress(Message mail) {
		try {
			return mail.getRecipients(RecipientType.CC);
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获所有收件人
	 * @param mail
	 * @return
	 */
	public HashMap<String, List<Address>> getAllReciverAddress(Message mail) {
		HashMap<String, List<Address>> resultMap = new HashMap<String, List<Address>>();
		try {
			resultMap.put(MailConstants.TO, Arrays.asList(mail.getRecipients(RecipientType.TO)));
			resultMap.put(MailConstants.CC, Arrays.asList(mail.getRecipients(RecipientType.CC)));
			resultMap.put(MailConstants.BCC, Arrays.asList(mail.getRecipients(RecipientType.BCC)));
			
			List<Address> all = new ArrayList<Address>();
			all.addAll(resultMap.get(MailConstants.TO));
			all.addAll(resultMap.get(MailConstants.CC));
			all.addAll(resultMap.get(MailConstants.BCC));
			
			resultMap.put(MailConstants.ALLRECIPIENT, all);
			
			return resultMap;
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取收件时间
	 * @param mail
	 * @return
	 */
	public Date getReciveDate(Message mail) {
		try {
			return mail.getReceivedDate();
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取发送时间
	 * @param mail
	 * @return
	 */
	public Date getSentDate(Message mail) {
		try {
			return mail.getSentDate();
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取邮件标题
	 * @param mail
	 * @return
	 */
	public String getSubject(Message mail) {
		try {
			return mail.getSubject();
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取邮件内容(所有)
	 * @param mail
	 * @return
	 */
	public MimeMultipart getMailContent(Message mail) {
		try {
			return (MimeMultipart) mail.getContent();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 获取邮件内容(文字)
	 * @param mail
	 * @return
	 */
	public String getMailContentText(Message mail) {
		try {
			return getMailContent(mail).getBodyPart(0).getContent().toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 关闭正在打开的邮箱文件夹
	 * @param folder
	 * @return
	 */
	public boolean closeFolder(Folder folder) {
		try {
			if(folder.isOpen()) {
				folder.close(false);
			}
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 关闭所有邮箱文件夹
	 * @return
	 */
	public boolean closeFolder(Store store) {
		Folder[] folders;
		try {
			folders = store.getDefaultFolder().list();
		} catch (MessagingException e1) {
			e1.printStackTrace();
			return false;
		}
		
		for(Folder folder : folders) {
			if(folder.isOpen()) {
				try {
					folder.close(true);
				} catch (MessagingException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 关闭邮箱连接
	 * @param store
	 * @return
	 */
	public boolean closeStore(Store store) {
		try {
			if(store.isConnected()) {
				store.close();
			} 
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
