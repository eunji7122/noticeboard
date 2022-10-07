package com.project.noticeboard.domain.comment;

import lombok.Data;

@Data
public class CommentUpdateDto {

    private String text;

    public CommentUpdateDto(String text) {
        this.text = text;
    }
}
