package io.nology.blog.blogpost;

import jakarta.validation.constraints.NotBlank;

public class UpdateBlogPostDTO {
    @NotBlank
    private String content;
    @NotBlank
    private String title;
    @NotBlank
    private String category;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "UpdateBlogPostDTO [content=" + content + ", title=" + title + ", category=" + category + "]";
    }
}
