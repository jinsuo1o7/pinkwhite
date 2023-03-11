package com.project.pinkwhite.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link ArticleWithCommentsDto} entity
 */
public record ArticleWithCommentsResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        String email,
        String nickname,
        Set<ArticleCommentResponse> articleComments,
        LocalDateTime createdAt
) {
    public static ArticleWithCommentsResponse of(Long id, String title, String content, String hashtag, String email, String nickname, Set<ArticleCommentResponse> articleCommentDtos, LocalDateTime createdAt) {
        return new ArticleWithCommentsResponse(id, title, content, hashtag, email, nickname, articleCommentDtos, createdAt);
    }

    public static ArticleWithCommentsResponse toRes(ArticleWithCommentsDto dto) {
        return ArticleWithCommentsResponse.of(dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.memberDto().email(),
                dto.memberDto().nickname(),
                dto.articleCommentDtos().stream()
                        .map(ArticleCommentResponse::toRes)
                        .collect(Collectors.toSet()),
                dto.createdAt());
    }
}