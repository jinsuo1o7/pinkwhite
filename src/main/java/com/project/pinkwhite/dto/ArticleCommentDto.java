package com.project.pinkwhite.dto;

import com.project.pinkwhite.domain.ArticleComment;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.pinkwhite.domain.ArticleComment} entity
 */
public record ArticleCommentDto(
        Long id,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy,
        MemberDto member
) {
    public static ArticleCommentDto of(Long id, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy, MemberDto member) {
        return new ArticleCommentDto(id, content, createdAt, modifiedAt, createdBy, modifiedBy, member);
    }

    public static ArticleCommentDto toDto(ArticleComment entity) {
        return ArticleCommentDto.of(entity.getId(), entity.getContent(), entity.getCreatedAt(), entity.getModifiedAt(), entity.getCreatedBy(), entity.getModifiedBy(), MemberDto.toDto(entity.getMember()));
    }
}