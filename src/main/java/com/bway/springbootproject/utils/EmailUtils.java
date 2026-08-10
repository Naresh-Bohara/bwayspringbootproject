package com.bway.springbootproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bway.springbootproject.model.Contact;

@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactEmail(Contact contact) {

        SimpleMailMessage mail = new SimpleMailMessage();

        // Send to the person who submitted the form
//        mail.setTo(contact.getEmail());
        
        // Email where you want to receive the contact message
        mail.setTo("nareshbohara3305@gmail.com");

        // Subject entered by the user
        mail.setSubject(contact.getSubject());

        // Email body
        mail.setText(
                "Hello " + contact.getName() + ",\n\n"
                + "Thank you for contacting us.\n\n"
                + "Your message:\n"
                + contact.getMessage()
                + "\n\n"
                + "Regards,\n"
                + "SpringBootProject"
        );

        mailSender.send(mail);
    }
}