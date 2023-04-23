package com.michael.springbootreactauthenticationjwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix ="rsa")
public record RsaKeys(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
