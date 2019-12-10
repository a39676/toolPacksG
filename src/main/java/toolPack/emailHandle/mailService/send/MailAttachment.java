package toolPack.emailHandle.mailService.send;

import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class MailAttachment {
	
	public Multipart createAttachments(String text, List<String> filePaths) {
		
		Multipart multipart = new MimeMultipart();
		try {
			
			BodyPart textPart = new MimeBodyPart();
			textPart.setText(text);
			multipart.addBodyPart(textPart);

			if(filePaths != null && filePaths.size() > 0) {
				BodyPart attachmentPart = null;
				DataSource source = null;
				for(String filePath : filePaths) {
					source = new FileDataSource(filePath);
					attachmentPart = new MimeBodyPart();
					attachmentPart.setDataHandler(new DataHandler(source));
					attachmentPart.setFileName(source.getName());
					multipart.addBodyPart(attachmentPart);
				}
			}
			
			return multipart;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}
	
	
	public Multipart createAttachments(List<String> fileNameList) {
	    return createAttachments(null, fileNameList);
	}

}
