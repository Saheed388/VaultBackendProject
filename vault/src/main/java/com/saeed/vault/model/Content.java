package com.saeed.vault.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    @NotBlank
    @Size(min = 5, message = "Title must be at least 5 characters long")
    private String title;

    @NotBlank(message = "Content body cannot be blank")
    @Lob // Use this if the content can be large (e.g., text or JSON)
    private String body; // Renamed from 'content' to 'body' for clarity

    private LocalDate postDate;

    @PrePersist // Automatically set the postDate when the entity is persisted
    protected void onCreate() {
        postDate = LocalDate.now();
    }
}