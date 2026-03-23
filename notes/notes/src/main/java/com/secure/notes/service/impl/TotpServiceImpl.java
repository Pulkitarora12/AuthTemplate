package com.secure.notes.service.impl;

import com.secure.notes.service.TotpService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.stereotype.Service;

@Service
public class TotpServiceImpl implements TotpService {

    private final GoogleAuthenticator gAuth;

    public TotpServiceImpl(GoogleAuthenticator gAuth) {
        this.gAuth = gAuth;
    }

    @Override
    public GoogleAuthenticatorKey generateKey() {
        return gAuth.createCredentials();
    }

    @Override
    public String getQrUrl(GoogleAuthenticatorKey secret, String username) {
        return GoogleAuthenticatorQRGenerator
                .getOtpAuthURL("Secure Notes Application", username, secret );
    }

    @Override
    public boolean verifyCode(String secret, int code) {
        return gAuth.authorize(secret, code);
    }
}
