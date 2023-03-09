package com.project.pinkwhite.repository;

import com.project.pinkwhite.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
