package com.saeed.vault.service;

import com.saeed.vault.model.Content;

import java.util.List;

public interface ContentService {

    Content saveContent(Content content);

    List<Content> getAllContent();

    Content getContentById(Long contentId);

    boolean deleteContentById(Long contentId);
}
