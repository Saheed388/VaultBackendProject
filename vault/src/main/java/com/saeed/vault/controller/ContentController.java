package com.saeed.vault.controller;

import com.saeed.vault.model.Content;
import com.saeed.vault.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Updated endpoint to follow REST conventions
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping ("post/contents")
    public ResponseEntity<Content> postContent(@RequestBody Content content) { // Added @RequestBody
        Content savedContent = contentService.saveContent(content); // Assuming you have a saveContent method in ContentService
        return ResponseEntity.ok(savedContent); // Return the saved content with a 200 OK response
    }

    public ResponseEntity<String> getAllContent(){

    }
}
