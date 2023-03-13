package com.project.pinkwhite.repository;

import com.project.pinkwhite.domain.Article;
import com.project.pinkwhite.dto.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static com.project.pinkwhite.domain.QArticle.*;
import static com.project.pinkwhite.domain.QMember.*;


public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public ArticleRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ArticleListDto> searchArticlesBy(SearchCondition condition, Pageable pageable) {
        Sort sort = pageable.getSort();
        boolean isDescending = false;
        if (sort.isSorted()) {
            Sort.Order order = sort.iterator().next();
            isDescending = order.isDescending();
        }

        List<ArticleListDto> dto = queryFactory
                .select(new QArticleListDto(article.id, article.title, article.content, article.hashtag, article.createdAt, member.nickname, member.accountId))
                .from(article)
                .join(article.member, member)
                .where(titleLike(condition.getTitle()),
                        contentLike(condition.getContent()),
                        hashtagLike(condition.getHashtag()),
                        nicknameLike(condition.getNickname()))
                .orderBy(isDescending ? article.createdAt.desc() : article.createdAt.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(article.count())
                .from(article)
                .leftJoin(article.member, member)
                .where(titleLike(condition.getTitle()),
                        contentLike(condition.getContent()),
                        hashtagLike(condition.getHashtag()),
                        nicknameLike(condition.getNickname()));
        return PageableExecutionUtils.getPage(dto, pageable, countQuery::fetchOne);
    }

    private BooleanExpression titleLike(String title) {
        return title != null ? article.title.likeIgnoreCase("%" + title + "%") : null;
    }

    private BooleanExpression contentLike(String content) {
        return content != null ? article.content.likeIgnoreCase("%" + content + "%") : null;
    }

    private BooleanExpression hashtagLike(String hashtag) {
        return hashtag != null ? article.hashtag.likeIgnoreCase("%" + hashtag + "%") : null;
    }

    private BooleanExpression nicknameLike(String nickname) {
        return nickname != null ? member.nickname.likeIgnoreCase("%" + nickname + "%") : null;
    }
}
