package com.project.pinkwhite.dto;

import com.project.pinkwhite.domain.Article;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link ArticleDto} entity
 */
public record ArticleDetailDto(
        Long id,
        String title,
        String content,
        String hashtag,
        MemberDto memberDto,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
    public static ArticleDetailDto of(Long id, String title, String content, String hashtag, MemberDto memberDto, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new ArticleDetailDto(id, title, content, hashtag, memberDto, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ArticleDetailDto toDto(Article entity) {
        return ArticleDetailDto.of(entity.getId(), entity.getTitle(), entity.getContent(), entity.getHashtag(), MemberDto.toDto(entity.getMember()), entity.getCreatedAt(), entity.getModifiedAt(), entity.getCreatedBy(), entity.getModifiedBy());
    }

    public Article toEntity() {
        return Article.of(title, content, hashtag, memberDto.toEntity());
    }
}