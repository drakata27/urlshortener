package online.aleksdraka.urlshortener.controllers;

import online.aleksdraka.urlshortener.models.ShortenedURL;
import online.aleksdraka.urlshortener.services.ShortenedURLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenedURLController {
    private final ShortenedURLService service;

    public ShortenedURLController(ShortenedURLService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenedURL> shortenURL(@RequestBody ShortenedURL url) {
        try {
            service.saveUrl(url);
            return new ResponseEntity<>(url, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
