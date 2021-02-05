package net.nlacombe.userws.service.impl;

import net.nlacombe.userws.api.validation.Password;
import net.nlacombe.userws.api.validation.Username;
import net.nlacombe.userws.service.EmailSenderService;
import net.nlacombe.userws.service.exception.MailServiceException;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@Transactional
public class EmailSenderServiceImpl implements EmailSenderService
{
	private static Logger logger = LoggerFactory.getLogger(EmailSenderServiceImpl.class);

	private class SendEmailTh extends Thread
	{
		private MimeMessage email;

		public SendEmailTh(MimeMessage email)
		{
			this.email = email;
		}

		@Override
		public void run()
		{
			try
			{
				String from = getPrettyPrintedEmailAddresses(email.getFrom());
				String to = getPrettyPrintedEmailAddresses(email.getAllRecipients());

				logger.debug("Sending email: from: \"" + from + "\", to: \"" + to + "\", subject: \"" + email.getSubject() + "\"");

				mailSender.send(email);

				logger.debug("Email sent.");
			}
			catch(Exception exception)
			{
				logger.error("Error while sending email", exception);
			}
		}

		private String getPrettyPrintedEmailAddress(Address emailAddress)
		{
			return emailAddress.toString();
		}

		private String getPrettyPrintedEmailAddresses(Address[] emailAddresses)
		{
			String prettyPrintedEmailAddresses = "";
			prettyPrintedEmailAddresses += "[";

			for(int i = 0; i < emailAddresses.length; i++)
			{
				prettyPrintedEmailAddresses += getPrettyPrintedEmailAddress(emailAddresses[i]);

				if(i != emailAddresses.length - 1)
					prettyPrintedEmailAddresses += ", ";
			}

			prettyPrintedEmailAddresses += "]";

			return prettyPrintedEmailAddresses;
		}
	}

	private final JavaMailSender mailSender;
	private final String senderEmail;
	private final String senderName;

    public EmailSenderServiceImpl(JavaMailSender mailSender, @Value("${email.sender.email}") String senderEmail, @Value("${email.sender.name}") String senderName) {
        this.mailSender = mailSender;
        this.senderEmail = senderEmail;
        this.senderName = senderName;
    }

    public void sendRegistrationEmail(@Email String email, @Username String username, @Password String password) throws MailServiceException
	{
		String subject = "Account Registration";

		String body = "";

		body += "Here is your registration information: <br />";
		body += "Username: " + username + "<br />";
		body += "Password: " + password + "<br />";

		sendEmail(email, subject, body);
	}

	private void sendEmail(String to, String subject, String body) throws MailServiceException
	{
		try
		{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message);

			messageHelper.setFrom(new InternetAddress(senderEmail, senderName));
			messageHelper.setTo(to);
			messageHelper.setSubject(subject);
			messageHelper.setText(body, true);

			logger.debug("Queueing email to be sent: from: \"" + senderEmail + "\", to: \"" + to + "\", subject: \"" + subject + "\"");

			//send email on separate thread so we do not wait for it.
			new SendEmailTh(message).start();
		}
		catch(MessagingException | UnsupportedEncodingException exception)
		{
			throw new MailServiceException(exception);
		}
	}
}
