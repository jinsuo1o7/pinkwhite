package com.project.pinkwhite.dto;

import com.project.pinkwhite.domain.Article;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link ArticleDto} entity
 */
public record ArticleListDto(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String nickname,
        String accountId
) {
    @QueryProjection
    public ArticleListDto(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String nickname, String accountId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.accountId = accountId;
    }
}