package com.saeed.vault.repository;

import com.saeed.vault.model.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    Content findByContentId(Long contentId);

    Page<Content> findByTitleLikeIgnoreCase(String s, Pageable pageDetails);


    // Custom query methods can be added here if needed
}