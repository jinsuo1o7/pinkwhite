package com.project.pinkwhite.domain;

import com.project.pinkwhite.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@Entity
public class ArticleComment extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "article_comment_id")
    private Long id;

    @Setter
    @Column(nullable = false, length = 500)
    private String content;

    @Setter
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    private ArticleComment(Long id, String content, Article article, Member member) {
        this.id = id;
        this.content = content;
        this.article = article;
        this.member = member;
    }
    public static ArticleComment of(Long id, String content, Article article, Member member) {
        return new ArticleComment(id, content, article, member);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment)) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
