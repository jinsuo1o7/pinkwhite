package com.project.pinkwhite.dto;

import lombok.Data;

@Data
public class SearchCondition {
    private String title;
    private String content;
    private String accountId;
    private String nickname;
    private String hashtag;

    public SearchCondition(String title, String content, String accountId, String nickname, String hashtag) {
        this.title = title;
        this.content = content;
        this.accountId = accountId;
        this.nickname = nickname;
        this.hashtag = hashtag;
    }

    public static SearchCondition of(String title, String content, String accountId, String nickname, String hashtag) {
        return new SearchCondition(title, content, accountId, nickname, hashtag);
    }
}
