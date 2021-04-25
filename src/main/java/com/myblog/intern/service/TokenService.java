package com.myblog.intern.service;

import com.myblog.intern.model.Token;
import com.myblog.intern.repository.TokenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {
    private static final BytesKeyGenerator tokenGenerator= KeyGenerators.secureRandom(60);
    private static final Charset US_ASCII= Charset.forName("US-ASCII");
    private String tokenValue;
    @Value("300")
    private int tokenValidityInSeconds;
    @Autowired
    private TokenRepository tokenRepository;

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public boolean createToken(String userName){
        if(tokenExists(userName)) return false;
        String tokenValue= new String(Base64.encodeBase64URLSafe(tokenGenerator.generateKey()), US_ASCII);
        this.tokenValue=tokenValue;
        Token token= new Token(userName, tokenValue);
        //token.setToken(tokenValue);
        token.setExpireAt(Timestamp.valueOf(LocalDateTime.now().plusSeconds(tokenValidityInSeconds)));
        tokenRepository.save(token);
        return true;
    }
    public Optional<Token> getToken(String token){
        return tokenRepository.findByToken(token);
    }
    public boolean tokenExists(String userName){
        Optional<Token> anyToken= tokenRepository.findByUserName(userName);
        if(anyToken.isPresent()){
            if(anyToken.get().getExpireAt().compareTo(Timestamp.valueOf(LocalDateTime.now()))<0){
                tokenRepository.deleteById(anyToken.get().getId());
            }
            else return true;
        }
        return false;
    }
    public void deleteByUserName(String userName){
        Optional<Token> anyToken= tokenRepository.findByUserName(userName);
        if(anyToken.isPresent()) tokenRepository.deleteById(anyToken.get().getId());
    }
}
