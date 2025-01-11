package online.aleksdraka.urlshortener.services;

import jakarta.transaction.Transactional;
import online.aleksdraka.urlshortener.dto.ShortenedUrlDTO;
import online.aleksdraka.urlshortener.models.ShortenedURL;
import online.aleksdraka.urlshortener.repositories.ShortenedURLRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ShortenedURLService {
    private final ShortenedURLRepository repository;

    public ShortenedURLService(ShortenedURLRepository repository) {
        this.repository = repository;
    }

    public ShortenedUrlDTO saveUrl(ShortenedURL url) {
        String randomCode = UUID.randomUUID().toString().substring(0, 6);
        String timeStamp = Instant.now().toString();

        url.setShortCode(randomCode);
        url.setCreatedAt(timeStamp);
        url.setUpdatedAt(timeStamp);

        ShortenedURL savedUrl = repository.save(url);
        return convertToDTO(savedUrl);
    }

    public ShortenedUrlDTO getShortenedURL(String shortCode) {
        try {
            ShortenedURL shortenedURL = repository.findByShortCode(shortCode);
            int currentCount = shortenedURL.getAccessCount();
            shortenedURL.setAccessCount(currentCount + 1);
            repository.save(shortenedURL);
            return convertToDTO(shortenedURL);
        } catch (Exception e) {
            return null;
        }
    }

    public ShortenedURL getShortenedUrlStats(String shortCode) {
        try {
            ShortenedURL shortenedURL = repository.findByShortCode(shortCode);
            int currentCount = shortenedURL.getAccessCount();
            shortenedURL.setAccessCount(currentCount + 1);
            repository.save(shortenedURL);
            return shortenedURL;
        } catch (Exception e) {
            return null;
        }
    }

    public ShortenedUrlDTO updateShortenedURL(
            String shortCode,
            ShortenedURL newURL
    ) {
        ShortenedURL existingURL = repository.findByShortCode(shortCode);
        if (existingURL == null) {
            return null;
        }
        existingURL.setUrl(newURL.getUrl());
        String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        existingURL.setUpdatedAt(timeStamp);
        repository.save(existingURL);
        return convertToDTO(existingURL);
    }

    @Transactional
    public void deleteShortenedURL(String shortCode) {
        repository.deleteByShortCode(shortCode);
    }

    public ShortenedUrlDTO convertToDTO(ShortenedURL entity) {
        return new ShortenedUrlDTO(
                entity.getId(),
                entity.getUrl(),
                entity.getShortCode(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
