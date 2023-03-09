package com.project.pinkwhite.repository;

import com.project.pinkwhite.dto.ArticleListDto;
import com.project.pinkwhite.dto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
    Page<ArticleListDto> searchArticlesBy(SearchCondition condition, Pageable pageable);
}
