package com.akash.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShortenedUrl {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String originalUrl;

    @Column(unique = true)
    private String shortUrl;

	public ShortenedUrl(Long id, String originalUrl, String shortUrl) {
		super();
		this.id = id;
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
	}

	public ShortenedUrl(String originalUrl, String shortUrl) {
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
	}
	
	public ShortenedUrl() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	@Override
	public String toString() {
		return "ShortenedUrl [id=" + id + ", originalUrl=" + originalUrl + ", shortUrl=" + shortUrl + "]";
	}
 

}
