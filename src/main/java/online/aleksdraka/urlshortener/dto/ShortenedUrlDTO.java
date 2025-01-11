package online.aleksdraka.urlshortener.dto;

public record ShortenedUrlDTO(
        Long id,
        String url,
        String shortCode,
        String createdAt,
        String updatedAt
) {
}
