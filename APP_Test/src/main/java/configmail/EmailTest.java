package configmail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
 
public class EmailTest {
	@AfterSuite
	@Test
	public static void main(String[] args) {
		
//		Utils.waitLongTime();
//		Utils.waitDefaultTime();
//		Utils.waitDefaultTime();
//		Utils.waitDefaultTime();
//		Utils.waitDefaultTime();
//		Utils.waitDefaultTime();
		// 创建一个Property文件对象
		Properties props = new Properties();
 
		// 设置邮件服务器的信息，这里设置smtp主机名称
		props.put("mail.smtp.host", "smtp.qq.com");
 
		// 设置socket factory 的端口
		props.put("mail.smtp.socketFactory.port", "465");
 
		// 设置socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 
		// 设置需要身份验证
		props.put("mail.smtp.auth", "true");
 
		// 设置SMTP的端口，QQ的smtp端口是25
		props.put("mail.smtp.port", "25");
 
		// 身份验证实现
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
 
			protected PasswordAuthentication getPasswordAuthentication() {
                        // 第二个参数，就是我QQ开启smtp的授权码
			//return new PasswordAuthentication("1697283524@qq.com","tylradogwdlqcbda");
			//return new PasswordAuthentication("1514743177@qq.com","vwcvjckszcmijaii");
			//return new PasswordAuthentication("tianshichuan@shanltech.com","W6qntDPyrYT9u65W");	
			return new PasswordAuthentication("1359911970@qq.com","okervmhflevkhhje");
			}
 
		});
 
		try {
 
			// 创建一个MimeMessage类的实例对象
			Message message = new MimeMessage(session);
 
			// 设置发件人邮箱地址
			// message.setFrom(new InternetAddress("1697283524@qq.com"));
			//message.setFrom(new InternetAddress("1514743177@qq.com"));
			message.setFrom(new InternetAddress("1359911970@qq.com"));
			
			// 设置收件人邮箱地址
			// message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("1697283524@qq.com"));
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("1514743177@qq.com"));
			//message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("1359911970@qq.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("tianshichuan@shanlitech.com"));
            // 设置邮件主题
			message.setSubject("自动化测试完成-发送测试报告，请查收");
 
			// 创建一个MimeBodyPart的对象，以便添加内容
			BodyPart messageBodyPart1 = new MimeBodyPart();
 
			// 设置邮件正文内容
			messageBodyPart1.setText("自动化执行测试完成，详情请查看附件");
 
			// 创建另外一个MimeBodyPart对象，以便添加其他内容
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
			// 设置邮件中附件文件的路径
			String filename = ".\\target\\${timestamp}\\html\\overview.html";
 
			// 创建一个datasource对象，并传递文件
			DataSource source = new FileDataSource(filename);
 
			// 设置handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// 加载文件
			messageBodyPart2.setFileName(filename);
 
			// 创建一个MimeMultipart类的实例对象
			Multipart multipart = new MimeMultipart();
 
			// 添加正文1内容
			multipart.addBodyPart(messageBodyPart1);
 
			// 添加正文2内容
			multipart.addBodyPart(messageBodyPart2);
 
			// 设置内容
			message.setContent(multipart);
 
			// 最终发送邮件
			Transport.send(message);
 
			System.out.println("=====邮件已经发送=====");
 
		} catch (MessagingException e) {
 
			   throw new RuntimeException(e);
 
		}
 
	}
 
}
