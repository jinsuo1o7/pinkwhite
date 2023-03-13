package com.project.pinkwhite.repository;

import com.project.pinkwhite.dto.ArticleListResponse;
import com.project.pinkwhite.dto.SearchCondition;
import com.project.pinkwhite.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.*;


@Slf4j
@DisplayName("biz logic - article")
class ArticleServiceTest {
    @InjectMocks private ArticleService articleService;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("searching")
    @Test
    void searchAll() {
        // Given
        SearchCondition cond = SearchCondition.of(null, null, null, null, null);
        PageRequest pageRequest = PageRequest.of(0, 10);

        // When
        Page<ArticleListResponse> responses = articleService.searchArticles(cond, pageRequest);
        for (ArticleListResponse response : responses) {
            log.info("{}",response);
        }
        // Then
        assertThat(responses.getSize()).isEqualTo(10);
    }
}