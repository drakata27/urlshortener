package online.aleksdraka.urlshortener.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
public class ShortenedURL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String url;

    String shortCode;

    private String createdAt;

    private String updatedAt;

    public ShortenedURL() {
    }

    public ShortenedURL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
