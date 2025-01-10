package online.aleksdraka.urlshortener.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortenedURL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    String url;
    String shortCode;

    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
