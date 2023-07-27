
package com.asm.controller;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.asm.domain.MailInfo;
import com.asm.service.MailerService;

@Controller
public class ContactController {

	@GetMapping("contact")
	public String showIndex(Model model, @ModelAttribute("mail") MailInfo mail) {
		return "contact";
	}
	@Autowired
	MailerService mailer;
	@Autowired
	ServletContext app;

	

	@PostMapping("mail/send")
	public String mailsend(Model model, @Validated @ModelAttribute("mail") MailInfo mail, BindingResult result ) {
		if (result.hasErrors()) {
            // Xử lý lỗi nếu có
            return "contact";
        }

        try {
            // Gửi email
        	mailer.send(mail);

            // Đặt thông báo thành công
            model.addAttribute("success_sendEmail", "Email sent successfully!");

        } catch (MessagingException e) {
            // Đặt thông báo lỗi
            model.addAttribute("error_sendEmail", "Failed to send email.");
        }

        return "contact";
	}
}
