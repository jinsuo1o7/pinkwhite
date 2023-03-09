package com.project.pinkwhite.repository;

import com.project.pinkwhite.dto.ArticleListDto;
import com.project.pinkwhite.dto.SearchCondition;
import com.project.pinkwhite.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

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
        Page<ArticleListDto> dtos = articleService.searchArticles(cond, pageRequest);
        for (ArticleListDto dto : dtos) {
            log.info("{}", dto);
        }

        // Then
        assertThat(dtos.getSize()).isEqualTo(10);
    }
}