package com.project.pinkwhite.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.pinkwhite.domain.ArticleComment} entity
 */
public record ArticleCommentResponse(
        Long id,
        String commentContent,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        // comment member
        String commenterNickname
) {
    public static ArticleCommentResponse of(Long id, String commentContent, LocalDateTime createdAt, LocalDateTime modifiedAt, String commenterNickname) {
        return new ArticleCommentResponse(id, commentContent, createdAt, modifiedAt, commenterNickname);
    }

    public static ArticleCommentResponse toRes(ArticleCommentDto dto) {
        return ArticleCommentResponse.of(dto.id(), dto.content(), dto.createdAt(), dto.modifiedAt(), dto.member().nickname());
    }
}