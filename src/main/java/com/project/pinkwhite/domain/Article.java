package com.project.pinkwhite.domain;

import com.project.pinkwhite.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
                @Index(columnList = "title"),
                @Index(columnList = "hashtag"),
                @Index(columnList = "createdAt"),
                @Index(columnList = "createdBy"),
})
@Entity
public class Article extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    @Setter
    @Column(nullable = false) private String title;
    @Setter
    @Column(nullable = false, length = 10000)
    private String content;
    @Setter
    private String hashtag;

    @ToString.Exclude
    @OrderBy(value = "createdAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @Setter
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Article(String title, String content, String hashtag, Member member) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.member = member;
    }

    public static Article of(String title, String content, String hashtag, Member member) {
        return new Article(title, content, hashtag, member);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id != null && getId().equals(article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
