package com.project.pinkwhite.controller;

import com.project.pinkwhite.dto.*;
import com.project.pinkwhite.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping
    public ResponseEntity<String> postArticle(@Valid @RequestBody ArticleRequest request) {
        String message = "Article saved successfully";
        articleService.saveArticle(request);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    public Page<ArticleListResponse> articleList(SearchCondition cond, @PageableDefault(size = 10, sort = "createdAt", direction = DESC) Pageable pageable) {
        return articleService.searchArticles(cond, pageable);
    }
    @GetMapping("/{articleId}")
    public ArticleWithCommentsResponse articleDetail(@PathVariable Long articleId) {
        return ArticleWithCommentsResponse.toRes(articleService.getArticleDetail(articleId));
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<String> updateArticle(@Valid @RequestBody ArticleDto dto, @PathVariable Long articleId) {
        String message = "Article updated successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long articleId) {
        String message = "Article deleted successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
