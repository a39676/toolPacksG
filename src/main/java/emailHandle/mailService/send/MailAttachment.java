package emailHandle.mailService.send;

import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class MailAttachment {
	
	public Multipart createAttachments(String... fileNames) {
		
		Multipart multipart = new MimeMultipart();
		try {
			
			BodyPart textPart = new MimeBodyPart();
			textPart.setText("This is message body");
			multipart.addBodyPart(textPart);

			BodyPart attachmentPart = null;
			DataSource source = null;
			for(String fileName : fileNames) {
				source = new FileDataSource(fileName);
				attachmentPart = new MimeBodyPart();
				attachmentPart.setDataHandler(new DataHandler(source));
				attachmentPart.setFileName(source.getName());
				multipart.addBodyPart(attachmentPart);
			}
			
			return multipart;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	
	public Multipart createAttachments(List<String> fileNameList) {
		
		String[] fileNames = new String[fileNameList.size()]; 
	    fileNames = fileNameList.toArray(fileNames);
	    
	    return createAttachments(fileNames);
	}

}
