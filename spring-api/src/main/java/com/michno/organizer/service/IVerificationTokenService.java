package com.michno.organizer.service;

import com.michno.organizer.model.User;
import com.michno.organizer.model.VerificationToken;

public interface IVerificationTokenService {
    void deleteVerificationToken(VerificationToken verificationToken);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);
}
