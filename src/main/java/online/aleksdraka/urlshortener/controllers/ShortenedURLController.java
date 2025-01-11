package online.aleksdraka.urlshortener.controllers;

import online.aleksdraka.urlshortener.dto.ShortenedUrlDTO;
import online.aleksdraka.urlshortener.models.ShortenedURL;
import online.aleksdraka.urlshortener.services.ShortenedURLService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShortenedURLController {
    private final ShortenedURLService service;

    public ShortenedURLController(ShortenedURLService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenedUrlDTO> shortenURL(@RequestBody ShortenedURL url) {
        try {
            ShortenedUrlDTO savedUrl = service.saveUrl(url);
            return new ResponseEntity<>(savedUrl, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/shorten/{shortCode}")
    public ResponseEntity<ShortenedUrlDTO> getShortenedURL(@PathVariable String shortCode) {
        ShortenedUrlDTO url = service.getShortenedURL(shortCode);
        if (url == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/shorten/{shortCode}/stats")
    public ResponseEntity<ShortenedURL> getShortenedURLStats(@PathVariable String shortCode) {
        ShortenedURL url = service.getShortenedUrlStats(shortCode);
        if (url == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @PutMapping("/shorten/{shortCode}")
    public ResponseEntity<?> updateShortenedURL(
            @PathVariable String shortCode,
            @RequestBody ShortenedURL url
    ) {
        try {
            ShortenedUrlDTO shortenedURL = service.updateShortenedURL(shortCode, url);
            if (shortenedURL == null) {
                return new ResponseEntity<>("URL with that Short Code was not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(shortenedURL, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/shorten/{shortCode}")
    public ResponseEntity<ShortenedURL> deleteShortenedURL(@PathVariable String shortCode) {
        service.deleteShortenedURL(shortCode);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
