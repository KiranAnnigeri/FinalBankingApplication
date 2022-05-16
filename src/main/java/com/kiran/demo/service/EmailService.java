package com.kiran.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kiran.demo.model.Details;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javamailSender;
	
	@Autowired
	private MABService mabService;
	
	public String sendEmail() {
		SimpleMailMessage message =new SimpleMailMessage();
		message.setFrom("smileyy2524@gmail.com");
		List<Details> a = mabService.checkActivitymab();
		List<String> emailList = a.stream().map(Details::getEmail).collect(Collectors.toList());
		System.out.println(emailList);
		//String emailString = String.join(",",emailList);
		//System.out.println(emailString);
		String[] emailArray = null;
		//emailArray = emailString.split(",");
		//System.out.println(emailArray);
		emailArray = emailList.toArray(new String[0]);
		message.setTo(emailArray);
		message.setSubject("This mail is regarding the promotional offers by ABCD bank");
		message.setText("Dear sir,\nGreetings from ABCD bank,\n\nWe want to bring to your notice that we have an ongoing NFO which is "
				+ "ABCD flexicap fund managed by Mr ABCD.\nIf interested write back to us at ....@gmail.com.\n\nRegards,\nABCD Bank.");
		javamailSender.send(message);
		
		return "Mail sent successfully to" + emailList + " on " + java.time.LocalDateTime.now();
	}
	
}
