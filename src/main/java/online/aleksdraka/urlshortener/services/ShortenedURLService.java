package online.aleksdraka.urlshortener.services;

import online.aleksdraka.urlshortener.models.ShortenedURL;
import online.aleksdraka.urlshortener.repositories.ShortenedURLRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ShortenedURLService {
    private final ShortenedURLRepository repository;

    public ShortenedURLService(ShortenedURLRepository repository) {
        this.repository = repository;
    }

    public void saveUrl(ShortenedURL url) {
        String randomCode = UUID.randomUUID().toString().substring(0, 6);
        String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        url.setShortCode(randomCode);
        url.setCreatedAt(timeStamp);
        url.setUpdatedAt(timeStamp);

        repository.save(url);
    }
}
