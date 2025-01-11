package online.aleksdraka.urlshortener.services;

import jakarta.transaction.Transactional;
import online.aleksdraka.urlshortener.models.ShortenedURL;
import online.aleksdraka.urlshortener.repositories.ShortenedURLRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ShortenedURLService {
    private final ShortenedURLRepository repository;
    private final Logger logger = Logger.getLogger(ShortenedURLService.class.getName());

    public ShortenedURLService(ShortenedURLRepository repository) {
        this.repository = repository;
    }

    public void saveUrl(ShortenedURL url) {
        String randomCode = UUID.randomUUID().toString().substring(0, 6);
        String timeStamp = Instant.now().toString();

        url.setShortCode(randomCode);
        url.setCreatedAt(timeStamp);
        url.setUpdatedAt(timeStamp);

        repository.save(url);
    }

    public ShortenedURL getShortenedURL(String shortCode) {
        return repository.findByShortCode(shortCode);
    }

    public ShortenedURL updateShortenedURL(
            String shortCode,
            ShortenedURL newURL
    ) {
        ShortenedURL existingURL = repository.findByShortCode(shortCode);


        existingURL.setUrl(newURL.getUrl());
        logger.info("Existing URL ID: " + existingURL.getId());

        String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        existingURL.setUpdatedAt(timeStamp);

        repository.save(existingURL);
        logger.info("Existing URL ID: " + existingURL.getId());
        return existingURL;
    }

    @Transactional
    public void deleteShortenedURL(String shortCode) {
        repository.deleteByShortCode(shortCode);
    }
}
