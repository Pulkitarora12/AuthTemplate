package com.secure.notes.service;

import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

public interface TotpService {

    GoogleAuthenticatorKey generateKey();

    String getQrUrl(GoogleAuthenticatorKey secret, String username);

    boolean verifyCode(String secret, int code);
}
