package com.project.Testiva.ServiceAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.Testiva.Model.StudentInfo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendAutoEmail {

	@Autowired
	private JavaMailSender mailSender;
	
	public void RegistrationApprovalEMail(StudentInfo studentInfo) throws MessagingException
	{
		String subject = "✅ Registration Approved – Welcome to Testiva Testb Portal";

		String message = "<!DOCTYPE html>" +
		"<html lang='en'>" +
		"<head>" +
		"<meta charset='UTF-8'>" +
		"<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
		"<style>" +
		"  body { background-color: #eef2f7; margin: 0; padding: 0; font-family: 'Segoe UI', sans-serif; }" +
		"  .email-wrapper { max-width: 650px; margin: 0 auto; background-color: #fff; border-radius: 12px; overflow: hidden; box-shadow: 0 8px 32px rgba(0,0,0,0.1); }" +
		"  .email-header { background: linear-gradient(to right, #1a73e8, #007ACC); color: white; text-align: center; padding: 20px; font-size: 20px; }" +
		"  .email-body { padding: 40px 30px; color: #444; }" +
		"  .email-body p { line-height: 1.6; font-size: 16px; margin-bottom: 16px; }" +
		"  .highlight { font-weight: bold; color: #2d3748; }" +
		"  .divider { height: 1px; background-color: #e2e8f0; margin: 30px 0; }" +
		"  .cta-button { display: inline-block; background-color: #007ACC; color: white; padding: 12px 24px; text-decoration: none; border-radius: 8px; font-size: 16px; margin-top: 20px; }" +
		"  .footer { text-align: center; font-size: 14px; color: #888; padding: 20px; background-color: #f8f9fa; }" +
		"</style>" +
		"</head>" +
		"<body>" +
		"  <div class='email-wrapper'>" +
		"    <div class='email-header'>🎉 Registration Approved</div>" +
		"    <div class='email-body'>" +
		"      <p>Dear <span class='highlight'>" + studentInfo.getName() + "</span>,</p>" +
		"      <p>We’re pleased to confirm that your registration for the <span class='highlight'>Testiva Test Portal</span> has been successfully approved by our administrator.</p>" +
		"      <p>Your account is now active and ready to explore. 🚀</p>" +
		"      <div class='divider'></div>" +
		"      <p><strong>What’s next?</strong></p>" +
		"      <ul>" +
		"        <li>🔑 Log into your dashboard</li>" +
		"        <li>📁 Access available resources</li>" +
		"        <li>💬 Connect with support if needed</li>" +
		"      </ul>" +
		"      <a href='http://localhost:8484/StudentLogin' class='cta-button'>Log In to Your Account</a>" +
		"      <div class='divider'></div>" +
		"      <p>Need assistance? Our support team is here to help.</p>" +
		"    </div>" +
		"    <div class='footer'>Testiva Test • Empowering smart digital solutions</div>" +
		"  </div>" +
		"</body>" +
		"</html>";
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setTo(studentInfo.getEmail());
		helper.setSubject(subject);
		helper.setText(message, true);
		
		mailSender.send(mimeMessage);
		
		
	}
}
