package com.project.pinkwhite.dto;

import com.project.pinkwhite.domain.Article;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.pinkwhite.domain.Article} entity
 */
public record ArticleDto(
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
    public static ArticleDto of(Long id, String title, String content, String hashtag, MemberDto memberDto, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new ArticleDto(id, title, content, hashtag, memberDto, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ArticleDto toDto(Article entity) {
        return ArticleDto.of(entity.getId(), entity.getTitle(), entity.getContent(), entity.getHashtag(), MemberDto.toDto(entity.getMember()), entity.getCreatedAt(), entity.getModifiedAt(), entity.getCreatedBy(), entity.getModifiedBy());
    }

    public Article toEntity() {
        return Article.of(title, content, hashtag, memberDto().toEntity());
    }
}