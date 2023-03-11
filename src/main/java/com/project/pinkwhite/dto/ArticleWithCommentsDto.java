package com.project.pinkwhite.dto;

import com.project.pinkwhite.domain.Article;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link ArticleDto} entity
 */
public record ArticleWithComments(
        Long id,
        String title,
        String content,
        String hashtag,
        MemberDto memberDto,
        Set<ArticleCommentDto> articleCommentDtos,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
    public static ArticleWithComments of(Long id, String title, String content, String hashtag, MemberDto memberDto, Set<ArticleCommentDto> articleWithComments, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new ArticleWithComments(id, title, content, hashtag, memberDto, articleWithComments, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ArticleWithComments toDto(Article entity) {
        return ArticleWithComments.of(entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                MemberDto.toDto(entity.getMember()),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::toDto)
                        .collect(Collectors.toSet()),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy());
    }

    public Article toEntity() {
        return Article.of(title, content, hashtag, memberDto.toEntity());
    }
}