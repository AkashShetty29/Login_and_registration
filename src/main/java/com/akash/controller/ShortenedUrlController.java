package com.akash.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.akash.Repo.ShortenedUrlRepository;
import com.akash.entity.ShortenedUrl;
import com.akash.service.UrlService;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class ShortenedUrlController {
	
    @Autowired
    private ShortenedUrlRepository shortenedUrlRepo;
    
    @Autowired
    private UrlService urlService;
    
    
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenURL(@RequestParam("url") String originalUrl) {
        ShortenedUrl urlMapping = new ShortenedUrl();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(urlService.generateShortURL());
        shortenedUrlRepo.save(urlMapping);
        return ResponseEntity.ok("Short URL:localhost:8085/" + urlMapping.getShortUrl());
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectURL(@PathVariable String shortUrl) {
    	
    	Optional<ShortenedUrl> urlmappingOptional = shortenedUrlRepo.findByShortUrl(shortUrl);
        if (urlmappingOptional.isPresent()) {
        	ShortenedUrl urlMapping = urlmappingOptional.get();
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", urlMapping.getOriginalUrl()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
