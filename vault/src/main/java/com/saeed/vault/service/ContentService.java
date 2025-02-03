package com.saeed.vault.service;

import com.saeed.vault.model.Content;
import com.saeed.vault.payload.ContentDTO;
import com.saeed.vault.payload.ContentResponse;

import java.util.List;

public interface ContentService {


    ContentResponse getAllContent(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ContentDTO savedContentDTO(ContentDTO contentDTO);

    ContentDTO getContentById(Long contentId);

    ContentDTO deleteContent(Long contentId);

    ContentResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
}
