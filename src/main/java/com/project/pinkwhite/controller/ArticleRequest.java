package com.project.pinkwhite.controller;

import com.project.pinkwhite.domain.Article;

/**
 * A DTO for the {@link com.project.pinkwhite.domain.Article} entity
 */
public record ArticleRequest(String title, String content, String hashtag) {
    public Article toEntity() {
        return Article.createArticle(title, content, hashtag);
    }
}