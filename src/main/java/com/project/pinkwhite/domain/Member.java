package com.project.pinkwhite.domain;

import com.project.pinkwhite.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Setter
    @Column(unique = true, nullable = false, length = 50)
    private String accountId;

    @Setter
    @Column(nullable = false)
    private String accountPw;

    @Setter
    @Column(nullable = false, length = 100)
    private String email;

    @Setter
    private String nickname;

    private Member(Long id, String accountId, String accountPw, String email, String nickname) {
        this.id = id;
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.email = email;
        this.nickname = nickname;
    }

    public static Member of(Long id, String accountId, String accountPw, String email, String nickname) {
        return new Member(id, accountId, accountPw, email, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return id != null && getId().equals(member.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
