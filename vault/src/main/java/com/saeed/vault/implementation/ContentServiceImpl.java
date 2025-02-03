package com.saeed.vault.implementation;

import com.saeed.vault.exception.APIException;
import com.saeed.vault.exception.ResourceNotFoundException;
import com.saeed.vault.model.Content;
import com.saeed.vault.payload.ContentDTO;
import com.saeed.vault.payload.ContentResponse;
import com.saeed.vault.repository.ContentRepository; // Add this import
import com.saeed.vault.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Autowired // Constructor injection is preferred for dependencies
    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Autowired
    private ModelMapper modelMapper;






//    @Override
//    public boolean deleteContentById(Long contentId) {
//        if(contentRepository.existsById(contentId)) {
//            contentRepository.deleteById(contentId);
//            return true;
//        }else
//            return false;
//        }

    @Override
    public ContentResponse getAllContent(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Content> contentPage = contentRepository.findAll(pageDetails);

        List<Content> categories = contentPage.getContent();
        if (categories.isEmpty())
            throw new APIException("No category created till now.");

        List<ContentDTO> contentDTOS = categories.stream()
                .map(category -> modelMapper.map(category, ContentDTO.class))
                .toList();

        ContentResponse contentResponse = new ContentResponse();
        contentResponse.setContent(contentDTOS);
        contentResponse.setPageNumber(contentPage.getNumber());
        contentResponse.setPageSize(contentPage.getSize());
        contentResponse.setTotalElements(contentPage.getTotalElements());
        contentResponse.setTotalPages(contentPage.getTotalPages());
        contentResponse.setLastPage(contentPage.isLast());
        return contentResponse;
    }

    @Override
    public ContentDTO savedContentDTO(ContentDTO contentDTO) {
        Content content = modelMapper.map(contentDTO, Content.class);
        Content contentFromDb = contentRepository.findByContentId(content.getContentId());
        if (contentFromDb != null)
            throw new APIException("Category with the name " + content.getContentId() + " already exists !!!");
        Content savedContent = contentRepository.save(content);
        return modelMapper.map(savedContent, ContentDTO.class);
    }

    @Override
    public ContentDTO getContentById(Long contentId) {
        return contentRepository.findById(contentId)
                .map(content -> modelMapper.map(content, ContentDTO.class)) // Convert entity to DTO
                .orElseThrow(() -> new ResourceNotFoundException("Content","contentId",contentId));
        }

    @Override
    public ContentDTO deleteContent(Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","contentId",contentId));

        contentRepository.delete(content);
        return modelMapper.map(content, ContentDTO.class);
    }
    }


