package com.akash.service;

import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService{
	
    public String generateShortURL() {
        // Generating a random UUID and encoding it to base64 to get a short URL
        UUID uuid = UUID.randomUUID();
        String base64UUID = Base64.getUrlEncoder().withoutPadding().encodeToString(uuid.toString().getBytes());
        // Take the first 8 characters as the short URL
        return base64UUID.substring(0, 8);
    }

}
