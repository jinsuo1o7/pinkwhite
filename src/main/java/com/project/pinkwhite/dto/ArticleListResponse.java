package com.project.pinkwhite.dto;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link ArticleDto} entity
 */
public record ArticleListResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String nickname
) {
    public static ArticleListResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String nickname) {
        return new ArticleListResponse(id, title, content, hashtag, createdAt, nickname);
    }

    public static ArticleListResponse toRes(ArticleListDto dto) {
        return ArticleListResponse.of(dto.id(), dto.title(), dto.content(), dto.hashtag(), dto.createdAt(), dto.nickname());
    }
}