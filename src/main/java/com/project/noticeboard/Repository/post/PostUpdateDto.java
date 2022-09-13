package com.project.noticeboard.Repository.post;

import lombok.Data;

@Data
public class PostUpdateDto {

    private String title;
    private String content;

    public PostUpdateDto() {
    }

    public PostUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
