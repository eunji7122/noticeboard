package com.project.noticeboard.Repository.post;

import lombok.Data;

@Data
public class PostSearchCond {

    private String title;

    public PostSearchCond() {
    }

    public PostSearchCond(String title) {
        this.title = title;
    }
}
