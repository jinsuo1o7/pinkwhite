package com.project.pinkwhite.service;

import com.project.pinkwhite.domain.Article;
import com.project.pinkwhite.dto.ArticleDetailDto;
import com.project.pinkwhite.dto.ArticleDto;
import com.project.pinkwhite.dto.ArticleListDto;
import com.project.pinkwhite.dto.SearchCondition;
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
    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Page<ArticleListDto> searchArticles(SearchCondition condition, Pageable pageable) {
        return articleRepository.searchArticlesBy(condition, pageable);
    }

    @Transactional(readOnly = true)
    public ArticleDetailDto getArticleDetail(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("Article Service getArticleDetail - entity not founded id : " + articleId));
        return ArticleDetailDto.toDto(article);
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
