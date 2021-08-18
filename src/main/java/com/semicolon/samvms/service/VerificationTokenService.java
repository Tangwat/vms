package com.semicolon.samvms.service;


import com.semicolon.samvms.domain.User;
import com.semicolon.samvms.domain.VerificationToken;
import com.semicolon.samvms.repository.UserRepository;
import com.semicolon.samvms.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class VerificationTokenService {
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final SendingMailService sendingMailService;

    @Autowired
    public VerificationTokenService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, SendingMailService sendingMailService){
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.sendingMailService = sendingMailService;
    }

    public void createVerification(String email){
        Optional<User> users = userRepository.findByEmail(email);
        User user = null;
        if (users.isPresent()) {
            user = users.get();
        }

        List<VerificationToken> verificationTokens = verificationTokenRepository.findByUserEmail(email);
        VerificationToken verificationToken;
        if (verificationTokens.isEmpty()) {
            verificationToken = new VerificationToken();
            verificationToken.setUser(user);
        } else {
            verificationToken = verificationTokens.get(0);
            verificationToken.setToken(new DecimalFormat("000000")
                    .format(new Random().nextInt(999999)));
            verificationToken.setIssuedDateTime(LocalDateTime.now());
            verificationToken.setExpiredDateTime(verificationToken.getIssuedDateTime().plusDays(1));
        }
        verificationTokenRepository.save(verificationToken);

        sendingMailService.sendVerificationMail(email, verificationToken.getToken());
    }

    public ResponseEntity<String> verifyAccount(String token){
        List<VerificationToken> verificationTokens = verificationTokenRepository.findByToken(token);
        if (verificationTokens.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        VerificationToken verificationToken = verificationTokens.get(0);
        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.unprocessableEntity().body("Expired token.");
        }

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
        verificationToken.getUser().setActive(true);
        verificationTokenRepository.save(verificationToken);

        return ResponseEntity.ok("Congratulations! Your account has been verified and activated!");
    }
}

