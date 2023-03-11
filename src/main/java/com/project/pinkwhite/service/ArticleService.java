package com.project.pinkwhite.service;

import com.project.pinkwhite.controller.ArticleRequest;
import com.project.pinkwhite.domain.Article;
import com.project.pinkwhite.dto.*;
import com.project.pinkwhite.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Transactional
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    public void saveArticle(ArticleRequest request) {
        articleRepository.save(request.toEntity());
    }

    @Transactional(readOnly = true)
    public Page<ArticleListResponse> searchArticles(SearchCondition condition, Pageable pageable) {
        return articleRepository.searchArticlesBy(condition, pageable).map(ArticleListResponse::toRes);
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleDetail(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Article Service getArticleDetail - entity not founded id : " + articleId));
    }

    public void updateArticle(ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.id());
            if (StringUtils.hasText(dto.title()))
                article.setTitle(dto.title());
            if (StringUtils.hasText(dto.content()))
                article.setContent(dto.content());
            article.setHashtag(dto.hashtag());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Article Service updateArticle - entity not founded id : " + dto.id());
        }
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
