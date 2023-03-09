package com.project.pinkwhite.dto;

import com.project.pinkwhite.domain.Member;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.pinkwhite.domain.Member} entity
 */
public record MemberDto(
        Long id,
        String accountId,
        String accountPw,
        String email,
        String nickname,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
    public static MemberDto of(Long id, String accountId, String accountPw, String email, String nickname, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new MemberDto(id, accountId, accountPw, email, nickname, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static MemberDto toDto(Member entity) {
        return MemberDto.of(entity.getId(), entity.getAccountId(), entity.getAccountPw(), entity.getEmail(), entity.getNickname(), entity.getCreatedAt(), entity.getModifiedAt(), entity.getCreatedBy(), entity.getModifiedBy());
    }

    public Member toEntity() {
        return Member.of(accountId, accountPw, email, nickname);
    }
}