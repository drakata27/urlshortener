package online.aleksdraka.urlshortener.repositories;

import online.aleksdraka.urlshortener.models.ShortenedURL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenedURLRepository extends JpaRepository<ShortenedURL, Long> {
}
