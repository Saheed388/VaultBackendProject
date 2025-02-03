package com.saeed.vault.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {

    private Long contentId;
    private String title;
    private String body;
    private LocalDate postDate;
}
