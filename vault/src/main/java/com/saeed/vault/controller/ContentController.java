package com.saeed.vault.controller;

import com.saeed.vault.model.Content;
import com.saeed.vault.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAll/contents")
    public ResponseEntity<List<Content>> getAllContent(){
        return new ResponseEntity<>(contentService.getAllContent(), HttpStatus.OK);

    }

    @GetMapping("/{contentId}")
    public ResponseEntity<Content> getContentById(@PathVariable Long contentId){
        Content content = contentService.getContentById(contentId);
        if (content != null)
            return new ResponseEntity<>(content, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
