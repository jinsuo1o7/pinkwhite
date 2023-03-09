package com.project.pinkwhite.repository;

import com.project.pinkwhite.config.AuditorConfig;
import com.project.pinkwhite.domain.Article;
import com.project.pinkwhite.domain.ArticleComment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DisplayName("JPA 연결 테스트")
@Import(AuditorConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final MemberRepository memberRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                             @Autowired ArticleCommentRepository articleCommentRepository,
                             @Autowired MemberRepository memberRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.memberRepository = memberRepository;
    }

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorkFine() {
        // Given

        // When
        List<Article> articles = articleRepository.findAll();
        List<ArticleComment> articleComments = articleCommentRepository.findAll();

        // Then
        assertThat(articles).hasSize(200);
        assertThat(articleComments).hasSize(700);
    }

    @DisplayName("update test")
    @Test
    void givenTestData_whenUpdating_thenWorkFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHash = "update hash tag";
        article.setHashtag(updateHash);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHash);
    }

    @DisplayName("delete test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        int articleCommentSize = article.getArticleComments().size();
        long prevArticleTotal = articleRepository.count();
        long prevCommentTotal = articleCommentRepository.count();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(prevArticleTotal - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(prevCommentTotal - articleCommentSize);
    }
}