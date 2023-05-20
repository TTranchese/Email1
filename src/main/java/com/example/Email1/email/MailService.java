package com.example.Email1.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private Environment environment;
	
	public String sendEmail(String receiver, String subject, String text) throws Exception {
		Email emailSender = new Email("tommasotranchese.dev@gmail.com");
		Email emailReceiver = new Email(receiver);
		Content content = new Content("text/plain", text);
		Mail mail = new Mail(emailSender, subject, emailReceiver, content);
		SendGrid sendGrid = new SendGrid(environment.getProperty("spring.sendgrid.api-key"));
		Request request = new Request();
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		Response response = sendGrid.api(request);
		return response.getBody();
	}
}
