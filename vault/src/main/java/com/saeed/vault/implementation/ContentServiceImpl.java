package com.saeed.vault.implementation;

import com.saeed.vault.model.Content;
import com.saeed.vault.repository.ContentRepository; // Add this import
import com.saeed.vault.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Autowired // Constructor injection is preferred for dependencies
    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content saveContent(Content content) {
        // Validate the content object before saving
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        // Save the content using the repository
        return contentRepository.save(content);
    }

    @Override
    public List<Content> getAllContent() {
        return contentRepository.findAll();
    }

    @Override
    public Content getContentById(Long contentId) {
        return contentRepository.findById(contentId).orElse(null);
    }

    @Override
    public boolean deleteContentById(Long contentId) {
        if(contentRepository.existsById(contentId)) {
            contentRepository.deleteById(contentId);
            return true;
        }else
            return false;
        }
    }
