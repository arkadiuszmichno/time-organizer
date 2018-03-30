package com.michno.organizer.util.registration;

import com.michno.organizer.model.User;
import com.michno.organizer.service.EmailService;
import com.michno.organizer.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationTokenService tokenService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationToken(user, token);
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "?token=" + token;

        String text = ("Please confirm your email address to complete registration process on Time-Organizer site: \n" +
                " Clink in this link:" + confirmationUrl);

        emailService.sendSimpleMessage(recipientAddress, subject, text);
    }
}
