package com.saeed.vault.controller;

import com.saeed.vault.config.AppConstants;
import com.saeed.vault.model.Content;
import com.saeed.vault.payload.ContentDTO;
import com.saeed.vault.payload.ContentResponse;
import com.saeed.vault.service.ContentService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ContentDTO> postContent(@Valid @RequestBody ContentDTO contentDTO) { // Added @RequestBody
        ContentDTO savedContentDTO = contentService.savedContentDTO(contentDTO); // Assuming you have a saveContent method in ContentService
        return ResponseEntity.ok(savedContentDTO); // Return the saved content with a 200 OK response
    }

    @GetMapping("/contents") // Updated endpoint to follow REST conventions
    public ResponseEntity<ContentResponse> getAllContent(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CONTENTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {

        ContentResponse contentResponse = contentService.getAllContent(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(contentResponse, HttpStatus.OK);
    }

    @GetMapping("/content/{contentId}")
    public ResponseEntity<ContentDTO> getContentById(@PathVariable Long contentId){
        ContentDTO contentDTO = contentService.getContentById(contentId);
        if (contentDTO != null)
            return new ResponseEntity<>(contentDTO, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/content/{contentId}")
    public ResponseEntity<ContentDTO> deleteContent(@PathVariable Long contentId){

        ContentDTO deletedContent = contentService.deleteContent(contentId);
        return new ResponseEntity<>(deletedContent, HttpStatus.OK);
    }



//
////    @GetMapping("/content/keyword/{keyword}")
////    public ResponseEntity<String> getContentByKeyword(@PathVariable Long contentId,
////                                                      @RequestParam ){
////
////    }

}
